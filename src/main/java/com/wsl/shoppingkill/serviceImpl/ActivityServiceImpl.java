package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.ObjectUtil;
import com.wsl.shoppingkill.domain.Activity;
import com.wsl.shoppingkill.domain.Advertise;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.mapper.ActivityMapper;
import com.wsl.shoppingkill.mapper.GoodsMapper;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.param.ActivityParam;
import com.wsl.shoppingkill.obj.param.ActivityUpdateParam;
import com.wsl.shoppingkill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingkill.obj.vo.ActivityVO;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.service.ActivityService;
import com.wsl.shoppingkill.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private SkuService skuService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<ActivityVO> getActivityAll(ActivityParam activityParam) {
        activityParam.setTime(LocalDateTime.now());
        return activityMapper.getActivityAll(activityParam);
    }

    @Override
    public List<KillGoodsVO> getActivityFuture() {
        LocalDateTime now = LocalDateTime.now().plusHours(1).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = now.plusHours(5);

        Set<String> keys = redisTemplate.keys(RedisEnum.GOODS_FUTURE + "*");
        if (CollectionUtils.isNotEmpty(keys)) {
            List<Object> redisList = redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
                redisConnection.openPipeline();
                keys.forEach(li -> redisConnection.get(li.getBytes()));
                return null;
            });
            List<KillGoodsVO> activitiesList = ObjectUtil.castList(redisList, KillGoodsVO.class);
            activitiesList = activitiesList.stream().filter(Objects::nonNull).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(activitiesList)) {
                return activitiesList;
            }
        }
        List<Activity> activities = activityMapper.selectList(new QueryWrapper<Activity>()
                .ge(Advertise.START_TIME, now)
                .le(Advertise.START_TIME, end));
        if (CollectionUtils.isEmpty(activities)) {
            return new ArrayList<>();
        }
        List<KillGoodsVO> goodsByActivity = getGoodsByActivity(activities);
        redisTemplate.executePipelined(new SessionCallback() {
            //管道操作
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (KillGoodsVO activity : goodsByActivity) {
                    if (activity.getId() != null) {
                        String s = RedisEnum.GOODS_FUTURE + activity.getId();
                        operations.opsForValue().set(s, activity, 1L, TimeUnit.HOURS);
                    }
                }
                return null;
            }
        });

        return goodsByActivity;
    }


    @Override
    public List<KillGoodsVO> getActivityDoing(LocalDateTime now) {
        if (now == null) {
            now = LocalDateTime.now();
        }
        List<Activity> activities = activityMapper.selectList(new QueryWrapper<Activity>()
                .le(Advertise.START_TIME, now)
                .ge(Advertise.END_TIME, now));
        if (CollectionUtils.isNotEmpty(activities)) {
            return getGoodsByActivity(activities);
        }
        return new ArrayList<>();
    }


    @Override
    public IPage<ActivityByGoodsVO> getActivityByGoods(Long current, Long size, Long id, String name) {
        return goodsMapper.getActivityBuyGoods(new Page<>(current, size), id, name);
    }


    @Override
    @MyLog(detail = "添加/更新一个活动", grade = LoggerEnum.WORN)
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateActivity(ActivityUpdateParam activity) {

        List<Activity> activityList = new ArrayList<>(8);
        //获取对应商品的SKU数量
        Sku sku = new Sku();
        Map<Long, List<Sku>> collect = sku.selectList(new QueryWrapper<Sku>().select(Sku.ID, Sku.NUM)
                .in(Sku.ID, activity.getSkuList()
                        .stream()
                        .map(ActivityUpdateParam.Sku::getId)
                        .map(String::valueOf)
                        .collect(Collectors.toList())
                )).stream().collect(Collectors.groupingBy(Sku::getId));

        log.info(activity.getSkuList().toString());
        //如果有交叉时段，则不更新
        List<Long> ids = new ArrayList<>();
        collect.values().forEach(li -> li.forEach(v -> ids.add(v.getId())));
        long count = activityMapper.selectList(new QueryWrapper<Activity>()
                .in(Activity.SKU_ID, ids)
                .le(Activity.START_TIME, activity.getStartTime())
                .ge(Activity.END_TIME, activity.getStartTime())
                .or()
                .in(Activity.SKU_ID, ids)
                .le(Activity.START_TIME, activity.getEndTime())
                .ge(Activity.END_TIME, activity.getEndTime())

        ).size();
        if (count > 0) {
            return false;
        }
        //遍历判断
        List<Sku> skuListTemp = new ArrayList<>();
        activity.getSkuList().forEach(li -> {
            Activity activityTemp = new Activity();

            activityTemp.setStartTime(activity.getStartTime())
                    .setEndTime(activity.getEndTime())
                    .setUpdateTime(LocalDateTime.now())
                    .setSkuId(li.getId());
            //设置id更新
            if (li.getAId() != null && li.getAId() > 0) {
                activityTemp.setId(li.getAId());
            } else {
                activityTemp.setCreatTime(LocalDateTime.now());
            }

            //价格>0才更新
            if (li.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                activityTemp.setPrice(li.getPrice());
            }

            //数量不能超库存数量
            //TODO: bug
            if (li.getTotalNum() <= collect.get(li.getId()).get(0).getNum()) {
                activityTemp.setTotalNum(li.getTotalNum()).setSellNum(li.getTotalNum());

            } else {
                activityTemp.setTotalNum(collect.get(li.getId()).get(0).getNum()).setSellNum(collect.get(li.getId()).get(0).getNum());
            }
            Sku sku1 = collect.get(li.getId()).get(0);
            skuListTemp.add(sku1.setNum(sku1.getNum() - activityTemp.getTotalNum()));
            activityList.add(activityTemp);
        });


        log.info(activityList.toString());
        //批量更新
        try {
            saveOrUpdateBatch(activityList);

            skuService.updateBatchById(skuListTemp);
            //TODO：如果redis有则更新redis

            return true;
        } catch (Exception e) {
            log.error("更新活动失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    @Override
    @MyLog(detail = "删除一个活动", grade = LoggerEnum.SERIOUS)
    @Transactional(rollbackFor = Exception.class)
    public boolean delActivity(Long[] ids) {
        try {
            //TODO：删除redis
            if (activityMapper.deleteBatchIds(Arrays.asList(ids)) == ids.length) {
                return true;
            } else {
                throw new Exception("删除出错");
            }

        } catch (Exception e) {
            log.error("活动删除失败,已开启手动回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean checkActivity(Long[] id) {
        return checkActivity(Arrays.asList(id));
    }

    @Override
    public boolean checkActivity(Long id) {
        Long[] arr = new Long[1];
        arr[0] = id;
        return checkActivity(arr);
    }

    @Override
    public boolean checkActivity(List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return false;
        }

        List<Activity> activities = activityMapper.selectBatchIds(id);

        if (CollectionUtils.isEmpty(activities)) {
            return false;
        }
        for (Activity activity : activities) {
            if (!activity.getStartTime().isAfter(LocalDateTime.now())) {
                //未开始
                return false;
            }
        }
        return true;

    }

    @Override
    public List<KillGoodsVO> getGoodsByActivity(List<Activity> activities) {
        Set<Long> skuIds = activities.stream().map(Activity::getSkuId).collect(Collectors.toSet());
        List<Sku> skus = skuMapper.selectBatchIds(skuIds);
        if (skus.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Long, Long> sku2goodsId = skus.stream().collect(Collectors.toMap(Sku::getId, Sku::getGoodsId));
        Map<Long, Goods> goodsMap = goodsMapper.selectList(new QueryWrapper<Goods>().in(Goods.ID, sku2goodsId.values()))
                .stream()
                .collect(Collectors.toMap(Goods::getId, Function.identity()));

        if (goodsMap.isEmpty()) {
            return new ArrayList<>();
        }
        List<KillGoodsVO> allVO = new ArrayList<>(64);
        activities.forEach(li -> {
            Long goodsId = sku2goodsId.get(li.getSkuId());
            if (goodsId != null && goodsId > 0) {
                Goods goods = goodsMap.get(goodsId);
                if (Objects.isNull(goods)) {
                    return;
                }
                KillGoodsVO killAvtivityVO = new KillGoodsVO();

                killAvtivityVO.setStartTime(li.getStartTime())
                        .setEndTime(li.getEndTime())
                        .setSum(li.getSellNum())
                        .setId(goodsId)
                        .setName(goods.getName())
                        .setImgUrl(goods.getImgUrl())
                        .setMinPrice(li.getPrice());
                allVO.add(killAvtivityVO);
            }
        });

        //处理最低价
        Map<String, List<KillGoodsVO>> money = allVO.stream().collect(Collectors.groupingBy(li -> li.getId() + li.getStartTime().toString()));

        //去重返回
        final ArrayList<KillGoodsVO> returnVO = allVO.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(
                                        () -> new TreeSet<>(Comparator.comparing(o -> o.getId() + ";" + o.getStartTime()))), ArrayList::new));

        returnVO.forEach(li -> {
            BigDecimal min = money.get(li.getId() + li.getStartTime().toString()).stream().map(KillGoodsVO::getMinPrice).min(Comparator.naturalOrder()).get();
            li.setMaxPrice(min).setMinPrice(min);
        });
        return returnVO;
    }

    @Override
    public boolean checkActivityByTime(ActivityUpdateParam activity) {
        List<Long> collect = activity.getSkuList().stream().map(ActivityUpdateParam.Sku::getAId).collect(Collectors.toList());
        List<Activity> activities = activityMapper.selectList(new QueryWrapper<Activity>().in(Activity.SKU_ID, collect).le(Activity.END_TIME, LocalDateTime.now().minusHours(12)));

        for (Activity li : activities) {
            if (activity.getStartTime().isBefore(li.getEndTime().plusHours(12)) || activity.getEndTime().plusHours(12).isAfter(li.getStartTime())) {
                return true;
            }
        }
        return false;
    }
}
