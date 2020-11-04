package com.wsl.shoppingKill.service;

import com.wsl.shoppingKill.constant.SexEnum;
import com.wsl.shoppingKill.domain.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
    private AdminService adminvice;

    @Test
    public void testAdmin(){
        //123,"123",1,"532923199701161916","18314263373","地址","sirwsl"
        Admin admin = new Admin();
        admin.setAddress("地址").setSex(SexEnum.MAN).setIdCard("532923199701161916")
                .setPassword("123").setPhone("18314263373").setWeChat("sirWsl");
        System.out.println(admin.toString());
        adminvice.insetAdmin(admin);
    }
    public void testgetAdmin(){
        System.out.println(adminvice.getAdmin(2L));
    }
}
