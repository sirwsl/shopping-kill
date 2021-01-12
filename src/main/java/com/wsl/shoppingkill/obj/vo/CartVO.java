package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/** 购物车VO
 * @author : WangShiLei
 * @date : 2020/12/28 4:30 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class CartVO implements Serializable {

    /**
     * 购物车标识
     */
    private Long id;

    private Long goodsId;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 物品名称
     */
    private String goodsName;

    /**
     *SKU名称
     */
    private String skuName;

    /**
     *图片地址
     */
    private String imgUrl;
}
