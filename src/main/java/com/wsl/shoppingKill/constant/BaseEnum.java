package com.wsl.shoppingKill.constant;

/**
 * @author WangShilei
 * @date 2020/11/10-18:33
 **/
public class BaseEnum {
    /**redis存储验证码**/
    public static final String REDIS_PREFIX = "verify:user:";
    public static final int CAPTCHA_EXPIRE_TIME = 5;

    /**
     * 手机号与IP 黑名单使用
     **/
    public static final int PHONE = 1;
    public static final int IP = 2;

    /**
     * 0-用户 1-管理员
     **/
    public static final Integer ADMIN = 1;
    public static final Integer CUSTOMER = 0;

    public static final String OSS_HEAD = "head/";
    public static final String OSS_ADVERTISE = "advertise/";
    public static final String OSS_SKU = "sku/";
    public static final String OSS_OTHER = "other/";

}
