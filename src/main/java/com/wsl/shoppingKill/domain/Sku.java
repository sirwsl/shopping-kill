package com.wsl.shoppingKill.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * SKU（商品的售卖产生的影响属性）
 * @author wangshilei
 * @date 2020/11/4 16:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku extends Model<Sku> {
    /**
    * SKU_ID
    */
    private Integer id;

    /**
    * 商品id
    */
    private Integer goodsId;

    /**
    * 商品属性
    */
    private String attribute;

    /**
    * 商品图片
    */
    private String imgUrl;

    /**
    * 进价
    */
    private BigDecimal realPrice;

    /**
    * 成本价
    */
    private BigDecimal costPrice;

    /**
    * 售价
    */
    private BigDecimal sellPrice;

    /**
    * 数量
    */
    private Integer num;

    /**
    * 预警量（默认10）
    */
    private Integer warnNum;

    /**
    * 快递费用
    */
    private BigDecimal expPrice;

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