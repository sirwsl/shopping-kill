package com.wsl.shoppingKill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wsl
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    /**
    * 订单id
    */
    private String id;

    /**
    * 会员id
    */
    private Long memberId;

    /**
    * 地址id
    */
    private Integer adressId;

    /**
    * 物品id
    */
    private Long goodId;

    /**
    * 物品数量
    */
    private Integer goodNum;

    /**
    * 运费金额
    */
    private BigDecimal freightMoney;

    /**
    * 应付金额（实际支付金额）
    */
    private BigDecimal payMoney;

    /**
    * 支付方式：0->未支付；1->支付宝；2->微信
    */
    private Integer payType;

    /**
    * 订单来源：0->PC订单；1->app订单
    */
    private Integer sourceType;

    /**
    * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
    */
    private Integer status;

    /**
    * 订单类型：0->正常订单；1->秒杀订单
    */
    private Integer orderType;

    /**
    * 备注
    */
    private String remarks;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 支付时间
    */
    private LocalDateTime payTime;

    /**
    * 交易账户号
    */
    private String payNum;

    /**
    * 物流公司(配送方式)
    */
    private String deliveryCompany;

    /**
    * 物流单号
    */
    private String deliveryNum;

    /**
    * 延长收货时间
    */
    private byte[] expandTime;

    /**
    * 可以获得的积分
    */
    private Integer integration;

    /**
    * 发票类型：0->不开发票；1->电子发票；2->纸质发票
    */
    private Integer billType;

    /**
    * 发票抬头
    */
    private String billHeader;

    /**
    * 发票内容
    */
    private String billContent;

    /**
    * 收票人电话
    */
    private String billReceiverPhone;

    /**
    * 收票人邮箱
    */
    private String billReceiverEmail;

    /**
    * 确认收货状态：0->未确认；1->已确认
    */
    private Integer confirmStatus;

    /**
    * 删除状态：0->未删除；1->已删除
    */
    private byte[] deleteStatus;

    /**
    * 发货时间
    */
    private LocalDateTime deliveryTime;

    /**
    * 确认收货时间
    */
    private LocalDateTime receiveTime;

    private static final long serialVersionUID = 1L;
}