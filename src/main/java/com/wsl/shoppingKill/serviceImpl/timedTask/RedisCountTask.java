package com.wsl.shoppingKill.serviceImpl.timedTask;

import com.wsl.shoppingKill.constant.RedisEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * 首页统计定时任务
 * @author WangShilei
 * @date 2020/11/24-14:29
 **/

@Configuration
@EnableScheduling
public class RedisCountTask {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "0 0 * * *")
    private void configureTasks() {
        stringRedisTemplate.opsForValue().set(RedisEnum.COUNT_USER_SUM,"0");
        stringRedisTemplate.opsForValue().set(RedisEnum.COUNT_ORDER_SUM,"0");
        stringRedisTemplate.opsForValue().set(RedisEnum.COUNT_OUT_SUM,"0");
    }
}
