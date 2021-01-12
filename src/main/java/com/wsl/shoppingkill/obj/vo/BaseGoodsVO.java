package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 展示界面goodsVO
 * @author WangShilei
 * @date 2020/12/22-16:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseGoodsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    protected Long id;

    /**
     * 商品名称
     */
    protected String name;


    /**
     * 商品最低价格
     */
    protected BigDecimal minPrice;

    /**
     * 商品最高价格
     */
    protected BigDecimal maxPrice;

    /**
     * 商品图片
     */
    protected String imgUrl;


}
