package com.wsl.shoppingkill.component.request;

import com.wsl.shoppingkill.obj.bo.UserBO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wsl
 */
@Component
public abstract class AbstractCurrentRequestComponent {


    @Resource
    protected StringRedisTemplate stringRedisTemplate;

    /**
     * 获取当前登陆用户信息
     *
     * @return 当前登录用户信息
     */
    public abstract UserBO getCurrentUser();
}
