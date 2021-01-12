package com.wsl.shoppingkill;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author : WangShiLei
 * @date : 2021/1/2 8:55 下午
 **/
@SpringBootTest
public class redisDelete {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test(){
        String key = "goods*";
        Set<String> keys = redisTemplate.keys(key);

//        key = "goods:kill:17*";
//        keys.addAll(redisTemplate.keys(key));
//        key = "goods:kill:18*";
//        keys.addAll(redisTemplate.keys(key));
//        key = "goods:doing:18*";
//        keys.addAll(redisTemplate.keys(key));


        redisTemplate.delete(keys);
    }
}
