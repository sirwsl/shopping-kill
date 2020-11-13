package com.wsl.shoppingKill.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 短信模板
 * @author WangShilei
 * @date 2020/11/13-17:22
 **/
@AllArgsConstructor
@Getter
public enum SmsEnum {
    /**
     * 消息订阅
     */
    SUBSCRIPTION(2227,"消息订阅"),
    /**
     *取消授权
     */
    REMOVE_AUTHORIZATION(2223,"取消授权"),
    /**
     *添加授权
     */
    ADD_AUTHORIZATION(2222,"添加授权"),
    /**
     *验证码
     */
    VERIFICATION_CODE(2224,"验证码"),
    /**
     *注册成功
     */
    REGISTRATION_SUCCESS(2225,"注册成功"),
    /**
     *下单成功
     */
    ADD_ORDER(2226,"下单成功"),
    /**
     *出库通知
     */
    OUT_STOCK(2227,"出库通知"),
    /**
     *简单短信验证
     */
    SIMPLE(355,"简单短信验证");


    private final int code;

    private final String description;
}
