package com.wsl.shoppingKill.serviceImpl.timedTask;

import com.wsl.shoppingKill.constant.RedisEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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
@EnableAsync

public class RedisCountTask {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Async
    @Scheduled(cron = "0 0 0 * * *")
    public void configureTasks() {
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_USER_SUM).set("0");
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_ORDER_SUM).set("0");
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_OUT_SUM).set("0");
    }
}
