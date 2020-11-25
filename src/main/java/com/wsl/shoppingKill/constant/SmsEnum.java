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
     * 尊敬的{1}用户您好，我们将于{2}举行{3}活动。详情请查看邮件
     * 消息订阅
     */
    SUBSCRIPTION(2270,"消息订阅"),

    /**
     *取消授权
     * 管理员已取消您{1}的账号：{2} 的管理权限，如还在使用请及时联系管理员
     */
    REMOVE_AUTHORIZATION(2223,"取消授权"),

    /**
     *添加授权
     * 尊敬的{1}，您好。管理员已为您创建账号，您的账号为:{2},密码为：{3},请登录系统进行重置密码
     */
    ADD_AUTHORIZATION(2222,"添加授权"),
    /**
     *验证码
     * 验证码：{1}，{2}分钟内有效，工作人员不会向您所要密码，请勿泄漏给他人使用。
     */
    VERIFICATION_CODE(2224,"验证码"),

    /**
     *注册成功
     * 欢迎您注册商品限时抢购系统，在这里我们竭诚为您提供满意的服务
     */
    REGISTRATION_SUCCESS(2225,"注册成功"),

    /**
     *下单成功
     * {1}用户您好，您已下单成功，请及时支付您的订单。
     */
    ADD_ORDER(2226,"下单成功"),

    /**
     *出库通知
     * 尊敬的{1}用户您好，您的包裹{2}已经出库，请及时关注商品物流信息
     */
    OUT_STOCK(2227,"出库通知"),

    /**
     * 修改信息
     * 管理员已为您的账号：{1}，修改信息，登录密码为：{2}，请勿将密码泄漏给他人，请登录系统进行密码重置与基本信息修改
     */
    UPDATE_INFO(2317,"修改会员信息"),

    /**
     * 提醒支付
     * 尊敬的{1}用户，您购买的商品：{2}还未支付，请抓紧时间进行支付
     */
    REMIND_PAY(2447,"提醒支付"),

    /**
     *
     * 尊敬的{1}用户，您购买的{2}商品还未对其进行评价，在此邀请您对其做出评价，谢谢！
     */
    REMIND_EVALUATION(2448,"提醒评价");


    private final int code;

    private final String description;
}
