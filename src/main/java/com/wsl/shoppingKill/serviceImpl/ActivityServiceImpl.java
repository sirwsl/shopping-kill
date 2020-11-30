package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.obj.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.Activity;
import com.wsl.shoppingKill.domain.Sku;
import com.wsl.shoppingKill.mapper.ActivityMapper;
import com.wsl.shoppingKill.mapper.GoodsMapper;
import com.wsl.shoppingKill.obj.param.ActivityParam;
import com.wsl.shoppingKill.obj.param.ActivityUpdateParam;
import com.wsl.shoppingKill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingKill.obj.vo.ActivityVO;
import com.wsl.shoppingKill.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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

    @Override
    public IPage<ActivityVO> getActivityAll(Long current, Long size, ActivityParam activityParam) {
        activityParam.setTime(LocalDateTime.now());
        return activityMapper.getActivityAll(new Page<>(current, size), activityParam);
    }

    @Override
    public IPage<ActivityByGoodsVO> getActivityByGoods(Long current, Long size, Long id, String name) {
        return goodsMapper.getActivityBuyGoods(new Page<>(current, size), id, name);
    }


    @Override
    @MyLog(detail = "添加/更新一个活动", grade = LoggerEnum.WORN)
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateActivity(ActivityUpdateParam activity) {

        List<Activity> activityList = new ArrayList<>(4);
        //获取对应商品的SKU数量
        Sku sku = new Sku();
        Map<Long, List<Sku>> collect = sku.selectList(new QueryWrapper<Sku>().select(Sku.ID, Sku.NUM)
                .in(Sku.ID,activity.getSkuList()
                        .stream()
                        .map(ActivityUpdateParam.Sku::getId)
                        .map(String::valueOf)
                        .collect(Collectors.toList())
        )).stream().collect(Collectors.groupingBy(Sku::getId));

        collect.forEach((k,v) -> System.err.println(k+"          "+v.toString()));

        //遍历判断
        activity.getSkuList().forEach(li -> {
            Activity activityTemp = new Activity();

            activityTemp.setStartTime(activity.getStartTime())
                    .setEndTime(activity.getEndTime())
                    .setUpdateTime(LocalDateTime.now())
                    .setSkuId(li.getId());
            //设置id更新
            if (activity.getId() != null && activity.getId() > 0) {
                activityTemp.setId(activity.getId());
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
                activityTemp.setTotalNum(li.getTotalNum());
            } else {
                activityTemp.setTotalNum(collect.get(li.getId()).get(0).getNum());
            }
            activityList.add(activityTemp);
        });

        //批量更新
        try {
            saveOrUpdateBatch(activityList);
            //TODO：如果redis有则更新redis

            return true;
        } catch (Exception e) {
            log.error("更新活动失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

    }

    @Override
    @MyLog(value = "#id", detail = "删除一个活动", grade = LoggerEnum.SERIOUS)
    @Transactional(rollbackFor = Exception.class)
    public boolean delActivity(Long id) {
        try {
            //TODO：删除redis
            if (activityMapper.deleteById(id) > 0) {
                return true;
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            log.error("活动删除失败,已开启手动回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public Integer checkActivity(Long id) {
        Activity activity = activityMapper.selectById(id);
        if (Objects.isNull(activity)){
            return null;
        }
        if (activity.getStartTime().isAfter(LocalDateTime.now())) {
            //未开始
            return 0;
        } else if (activity.getStartTime().isBefore(LocalDateTime.now()) && activity.getEndTime().isAfter(LocalDateTime.now())) {
            //进行中
            return 1;
        } else if (activity.getEndTime().isBefore(LocalDateTime.now())) {
            //已结束
            return 2;
        }
        return null;
    }
}
