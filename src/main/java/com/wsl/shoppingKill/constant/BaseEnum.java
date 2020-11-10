package com.wsl.shoppingKill.constant;

/**
 * @author WangShilei
 * @date 2020/11/10-18:33
 **/
public class BaseEnum {
    public static final String REDIS_PREFIX = "verify:user:";
    public static final int CAPTCHA_EXPIRE_TIME = 5;

    /**
     * 手机号
     **/
    public static final int PHONE = 1;
    public static final int IP = 2;

    /**0-用户 1-管理员**/
    public static final Integer ADMIN = 1;

    public static final Integer CUSTOMER = 0;

}
