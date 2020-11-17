package com.wsl.shoppingKill.obj.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SkuVO
 * @author WangShilei
 * @date 2020/11/17-16:54
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SkuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * SKU_ID
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 物品名称
     */
    private String goodsName;

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


}
