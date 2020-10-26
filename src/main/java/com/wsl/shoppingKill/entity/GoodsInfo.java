package com.wsl.shoppingKill.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo implements Serializable {
    /**
    * 属性id
    */
    private Long id;

    /**
    * 商品id
    */
    private Long goodsId;

    /**
    * 商品属性描述
    */
    private String name;

    /**
    * 库存数量
    */
    private Integer num;

    /**
    * 售价
    */
    private BigDecimal sellMoney;

    /**
    * 告警量
    */
    private Integer warning;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 跟新时间
    */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}