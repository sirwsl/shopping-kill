package com.wsl.shoppingKill.service;

import com.wsl.shoppingKill.constant.SexEnum;
import com.wsl.shoppingKill.domain.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
        //123,"123",1,"532923199701161916","18314263373","地址","sirWsl"
        Admin admin = new Admin();
        admin.setAddress("地址").setIdCard("53292681136").setSex(SexEnum.MAN)
                .setPassword("123").setPhone("18888677861").setWeChat("sirWsl").setDelFlag(true);
        System.out.println(admin.toString());
        advice.insetAdmin(admin);
        System.err.println("存储结束");
        System.out.println(advice.getAdmin("18888677861"));

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
        System.out.println(redisTemplate.opsForValue().get("TestUser.1888867786").toString());


    }

    @Test
    public void testGetValue(){
        Admin admin = new Admin();
        System.out.println(advice.str(admin.setPhone("18888677861")));
    }
}
