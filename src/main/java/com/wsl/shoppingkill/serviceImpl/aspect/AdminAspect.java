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
public class AdminAspect {

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;


    /**
     * 发送短信时进行权限判断
     * @author wangShilei
     * @date 2020/11/24 14:42
     */
    @Pointcut("execution(public * com.wsl.shoppingkill.controller.admin.AdminController.addAdmin(..))")
    public void addAdmin(){
    }

    @Before("addAdmin()")
    public void beforeAddAdmin(){
        if(Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                && abstractCurrentRequestComponent.getCurrentUser().getFlag()==1000){
            throw new ExperienceException("体验账号权限不足");
        }
    }

    @Pointcut("execution(public * com.wsl.shoppingkill.controller.admin.AdminController.delAdmin(..))")
    public void delAdmin(){
    }

    @Before("delAdmin()")
    public void beforeDelAdmin(){
        if(Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                && abstractCurrentRequestComponent.getCurrentUser().getFlag()!=1){
            throw new ExperienceException("账号权限不足");
        }
    }

    @Pointcut("execution(public * com.wsl.shoppingkill.controller.admin.AdminController.updateAdmin(..))")
    public void updateAdmin(){
    }

    @Before("updateAdmin()")
    public void beforeUpdateAdmin(){
        if(Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                && abstractCurrentRequestComponent.getCurrentUser().getFlag()!=1){
            throw new ExperienceException("账号权限不足");
        }
    }
}
