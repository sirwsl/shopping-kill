package com.wsl.shoppingkill.service;

import com.wsl.shoppingkill.obj.constant.SexEnum;
import com.wsl.shoppingkill.domain.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author : WangShiLei
 * @date : 2020/11/4 11:01 下午
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Resource
    private AdminService advice;

    @Resource
    RedisTemplate<String,Admin> redisTemplate;

    @Test
    public void testAdmin(){

        Admin admin = new Admin();
        admin.setAddress("地址").setIdCard("53292681136").setSex(SexEnum.MAN)
                .setPassword("123").setPhone("18888677861").setWeChat("sirWsl").setDelFlag(true);
        System.out.println(admin.toString());

        System.err.println("存储结束");

    }
    @Test
    public void testedAdmin(){
        Admin admin = new Admin();
        admin.setAddress("地址").setIdCard("532923145786516136")
                .setPassword("123").setPhone("18314263478").setWeChat("sirWsl");
        System.out.println(admin.toString());

        redisTemplate.opsForValue().set("testRedis",admin);
        System.err.println("存值结束，开始取值");
        Admin admin1 = redisTemplate.opsForValue().get("testRedis");
        System.out.println(admin1);
    }


    @Test
    public void testGetValueForJava(){
        System.out.println(Objects.requireNonNull(redisTemplate.opsForValue().get("TestUser.1888867786")).toString());


    }


    @Test
    public void deleteLogicAdmin(){
        Admin admin  = new Admin();
        admin.deleteById(23);
    }
}
