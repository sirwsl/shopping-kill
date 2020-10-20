package com.wsl.shoppingkill;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class ShoppingKillApplicationTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void redisTest(){
        log.info("进入");
        stringRedisTemplate.opsForValue().set("test","sirwsl");
        String test = stringRedisTemplate.opsForValue().get("test");
        System.err.println("------------"+test);
        System.out.println("------------"+test);

    }
}
