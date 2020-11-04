package com.wsl.shoppingKill.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 订单表
 * @author wangshilei
 * @date 2020/11/4 16:43
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Model<Order> {
    /**
    * 订单id
    */
    private String id;

    /**
    * 买家id
    */
    private Integer userId;

    /**
    * sku_id
    */
    private Integer skuId;

    /**
    * 购买数量
    */
    private Integer num;

    /**
    * 下单时间
    */
    private LocalDateTime orderTime;

    /**
    * 支付时间
    */
    private LocalDateTime payTime;

    /**
    * 发货时间
    */
    private LocalDateTime sendTime;

    /**
    * 支付类型 (0-支付宝 1-微信 2-银行...)
    */
    private Integer payType;

    /**
    * 地址id
    */
    private Integer addressId;

    /**
    * 支付金额
    */
    private BigDecimal payPrice;

    /**
    * 备注
    */
    private String remark;

    /**
    * 订单状态(0-已下单未支付 1-已支付 2-已出库 3-已收货 4-已评价)
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    private Boolean delFlag;
}