package com.wsl.shoppingKill.obj.bo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单BO
 * @author wangshilei
 * @date 2020/11/4 16:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class OrderBO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
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

}
