package com.wsl.shoppingkill.serviceImpl.aspect;

import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.obj.exception.ExperienceException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author WangShilei
 * @date 2020/11/24-14:10
 **/
@Aspect
@Component
public class ExperienceAspect {

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;


    /**
     * 发送短信时进行权限判断
     * @author wangShilei
     * @date 2020/11/24 14:42
     */
    @Pointcut("execution(public * com.wsl.shoppingkill.component.sms.SmsComponent.send(..))")
    public void send(){
    }

    @Before("send()")
    public void beforeSend(){
        if(Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                && abstractCurrentRequestComponent.getCurrentUser().getFlag()==1000){
            throw new ExperienceException("体验账号权限不足");
        }
    }

}
