package com.wsl.shoppingKill.confTest;

import com.wsl.shoppingKill.entity.TestEntity;
import com.wsl.shoppingKill.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class RedisTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    TestService service;


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
    @Test
    public void test1(){
        stringRedisTemplate.opsForValue().set("name","sirwsl");
        String test = stringRedisTemplate.opsForValue().get("name");
        System.err.println("------------"+test);
        System.out.println("------------"+test);
    }

    @Test
    public void test2(){
        TestEntity testEntity = new TestEntity(123,"123","ewqwefqwefqwefqwfq");
        System.out.println(service.insertTest(testEntity));

    }

    @Test
    public void test3(){
        TestEntity testEntity = new TestEntity(123,"123","ewqwefqwefqwefqwfq");
        redisTemplate.opsForValue().set("user1",testEntity);
        System.out.println("取结果"+redisTemplate.opsForValue().get("user1"));
        TestEntity user = (TestEntity) redisTemplate.opsForValue().get("user1");
        System.out.println(user.getText());

    }
}
