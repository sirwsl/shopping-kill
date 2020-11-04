package com.wsl.shoppingKill.constant;

/**
 * @author wsl
 */

public enum Constants {
    //用户验证码
    Cache;
    public static final String REDIS_PREFIX = "verify:user:";
    public static final int CAPTCHA_EXPIRE_TIME = 5;
}
