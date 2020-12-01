package com.wsl.shoppingkill.obj.constant;

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
    public static final Integer USER = 0;

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


    /**
     * 订单状态(1-已下单未支付 2-已支付 3-已出库 4-已收货 5-已评价)
     */
    public static final Integer ORDER_TYPE_CANCEL = 0;
    public static final Integer ORDER_TYPE_NOT_PAY =1;
    public static final Integer ORDER_TYPE_PAY = 2;
    public static final Integer ORDER_TYPE_OUT = 3;
    public static final Integer ORDER_TYPE_GET = 4;
    public static final Integer ORDER_TYPE_TELL = 5;
    /**
     * 订单开始结束数字
     */
    public static final Integer ORDER_TYPE_BEGIN = 0;
    public static final Integer ORDER_TYPE_END = 5;
}
