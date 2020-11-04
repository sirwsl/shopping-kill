package com.wsl.shoppingKill.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 * @author wangshilei
 * @date 2020/11/4 16:43
 **/
@TableName("t_order")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Order extends Model<Order> implements Serializable {
    /**
    * 订单id
    */
    @Id
    @TableId()
    private String id;

    /**
    * 买家id
    */
    private Long userId;

    /**
    * sku_id
    */
    private Long skuId;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;
}