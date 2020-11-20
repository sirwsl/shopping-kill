package com.wsl.shoppingKill.constant;

/**
 * @author WangShilei
 * @date 2020/11/10-18:33
 **/
public class BaseEnum {
    /**
     * 手机号与IP
     **/
    public static final Integer EMAIL = 0;
    public static final Integer PHONE = 1;
    public static final Integer IP = 2;

    /**
     * 0-用户 1-管理员
     **/
    public static final Integer ADMIN = 1;
    public static final Integer CUSTOMER = 0;

    /**
     * 成功失败
     */
    public static final Integer SUCCESS = 1;
    public static final Integer MISS = 0;

    /**
     * OSS存储
     */
    public static final String OSS_HEAD = "head/";
    public static final String OSS_ADVERTISE = "advertise/";
    public static final String OSS_SKU = "sku/";
    public static final String OSS_GOODS = "goods/";
    public static final String OSS_OTHER = "other/";

    /**
     * 3-退款退货
     * 2-换货
     * 1-退钱
     */
    public static final Integer REFUND_GOODS_MONEY = 3;
    public static final Integer REFUND_GOODS = 2;
    public static final Integer REFUND_MONEY = 1;

    /**
     * 0-未解决
     * 1-已解决
     */
    public static final Integer UNSOLVED = 0;
    public static final Integer SOLVED = 1;
}
