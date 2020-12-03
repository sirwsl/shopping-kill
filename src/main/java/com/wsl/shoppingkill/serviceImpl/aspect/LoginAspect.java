package com.wsl.shoppingkill.serviceImpl.aspect;

import com.wsl.shoppingkill.obj.constant.RedisEnum;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/24-14:10
 **/
@Aspect
@Component
public class LoginAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 登录后redis计数器+1
     * @author wangShilei
     * @date 2020/11/24 14:42
     */
    @Pointcut("execution(public * com.wsl.shoppingkill.controller.LoginController.userLogin(..))")
    public void userLogin(){

    }

    @After("userLogin()")
    public void afterUserLogin(){
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_USER_SUM).increment(1);
    }


    @Pointcut("execution(public * com.wsl.shoppingkill.controller.LoginController.adminLogin(..))")
    public void adminLogin(){

    }

    @After("adminLogin()")
    public void afterAdminLogin(){
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_USER_SUM).increment(1);
    }


}
