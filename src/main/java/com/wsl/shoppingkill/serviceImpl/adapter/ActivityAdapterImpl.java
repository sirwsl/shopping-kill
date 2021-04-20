package com.wsl.shoppingkill.serviceImpl.adapter;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wsl.shoppingkill.common.util.ObjectUtil;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.service.ActivityService;
import com.wsl.shoppingkill.service.adapter.ActivityAdapter;
import com.wsl.shoppingkill.service.async.AsyncService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : WangShiLei
 * @date : 2020/12/27 6:33 下午
 **/

@Service
public class ActivityAdapterImpl implements ActivityAdapter {

    @Resource
    private ActivityService activityService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private AsyncService asyncService;

    @Override
    public List<KillGoodsVO> getActivityDoing() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String key = RedisEnum.GOODS_DOING;

        Set<String> keys = redisTemplate.keys(key + "*");

        if (CollectionUtils.isNotEmpty(keys)) {
            List<Object> redisList = redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
                redisConnection.openPipeline();
                keys.forEach(li -> redisConnection.get(li.getBytes()));
                return null;
            });
            List<KillGoodsVO> activitiesList = ObjectUtil.castList(redisList, KillGoodsVO.class);
            activitiesList = activitiesList.stream().filter(Objects::nonNull).collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(activitiesList)) {
                return activitiesList.stream().filter(li -> li.getEndTime().isAfter(localDateTime)).collect(Collectors.toList());
            }
        }

        List<KillGoodsVO> activityDoing = activityService.getActivityDoing(localDateTime);
        if (CollectionUtils.isNotEmpty(activityDoing)) {
            asyncService.killGoodsSync(activityDoing, localDateTime);
        }
        return activityDoing;
    }
}
