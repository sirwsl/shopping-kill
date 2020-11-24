package com.wsl.shoppingKill.serviceImpl.aspect;

import com.wsl.shoppingKill.constant.RedisEnum;
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
    @Pointcut("execution(public * com.wsl.shoppingKill.controller.LoginController.userLogin(..))")
    public void userLogin(){

    }

    @After("userLogin()")
    public void afterUserLogin(){
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_USER_SUM).increment(1);
    }


}
