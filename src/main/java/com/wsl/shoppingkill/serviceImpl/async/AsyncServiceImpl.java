package com.wsl.shoppingkill.serviceImpl.async;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wsl.shoppingkill.common.util.DateUtil;
import com.wsl.shoppingkill.mapper.ActivityMapper;
import com.wsl.shoppingkill.obj.bo.KillGoodsBO;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.obj.vo.ViewGoodsVO;
import com.wsl.shoppingkill.service.ActivityService;
import com.wsl.shoppingkill.service.async.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 * @date 2020/12/24-11:20
 **/
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivityService activityService;

    @Override
    @Async("taskExecutor")
    public void searchGoods(List<ViewGoodsVO> list) {
        redisTemplate.executePipelined(new SessionCallback() {
            //管道操作
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (ViewGoodsVO goods : list) {
                    operations.opsForValue().set(RedisEnum.SEARCH_GOODS + goods.getName(), goods, 5L, TimeUnit.MINUTES);
                }
                return null;
            }
        });
        log.info("查询商品信息同步结束");
    }

    @Override
    @Async("taskExecutor")
    public void goodsAll(IPage<ViewGoodsVO> goods) {
        redisTemplate.opsForValue().set(RedisEnum.GOODS_VIEW + goods.getCurrent(), goods, 5, TimeUnit.SECONDS);
    }

    @Override
    @Async("taskExecutor")
    public void getActivityFuture() {
        activityService.getActivityFuture();
    }


    @Override
    @Async("taskExecutor")
    @Transactional(rollbackFor = Exception.class)
    public void killGoodsSync(List<KillGoodsVO> activityDoing, LocalDateTime localDateTime) {
        if (localDateTime == null) {
            localDateTime = LocalDateTime.now();
        }
        if (CollectionUtils.isNotEmpty(activityDoing)) {

            List<KillGoodsBO> killGoods = activityMapper.getKillGoods(
                    activityDoing.stream().map(KillGoodsVO::getId).collect(Collectors.toList()),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime));
            try {
                /* 插入多条数据 */
                redisTemplate.executePipelined(new SessionCallback<Object>() {
                    @Override
                    public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                        //商品id缓存
                        for (KillGoodsVO k : activityDoing) {
                            Object o = redisTemplate.opsForValue().get(RedisEnum.GOODS_DOING + k.getId());
                            if (Objects.isNull(o)) {
                                redisTemplate.opsForValue().set(RedisEnum.GOODS_DOING + k.getId(), k, DateUtil.distanceSecond(LocalDateTime.now(), k.getEndTime()), TimeUnit.SECONDS);
                            }
                        }
                        //SKUid缓存
                        for (KillGoodsBO k : killGoods) {
                            LocalDateTime now = LocalDateTime.now();
                            Object o = redisTemplate.opsForValue().get(RedisEnum.GOODS_KILL + k.getId());
                            if (Objects.isNull(o)) {
                                redisTemplate.opsForValue().set(RedisEnum.GOODS_KILL + k.getId()
                                        , k.getNum(), DateUtil.distanceSecond(now, k.getEnd()), TimeUnit.SECONDS);
                                redisTemplate.opsForValue().set(RedisEnum.GOODS_KILL_TIME + k.getId()
                                        , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(k.getStart()), DateUtil.distanceSecond(now, k.getEnd()), TimeUnit.SECONDS);
                            }
                        }
                        return null;
                    }
                });
                log.info("秒杀商品信息同步结束");
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                log.info("秒杀商品信息同步失败");
            }
        }


    }

    @Override
    @Async("taskExecutor")
    public void killGoodsCheck() {
        List<KillGoodsVO> activityDoing = activityService.getActivityDoing(LocalDateTime.now());
        if (CollectionUtils.isNotEmpty(activityDoing)) {
            killGoodsSync(activityDoing, LocalDateTime.now());
        }
    }
}
