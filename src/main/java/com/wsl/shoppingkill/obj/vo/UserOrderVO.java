package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : WangShiLei
 * @date : 2020/12/30 9:52 上午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class UserOrderVO  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private Long orderId;

    private Long goodsId;

    /**
     * 订单创建时间
     */
    private LocalDateTime creatTime;

    /**
     * 收件人
     */
    private String userName;

    /**
     * 物品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String imgUrl;

    /**
     * 支付单价
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 物流费用
     */
    private BigDecimal logisticsPrice;
    /**
     * 总支付金额
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态
     */
    private String status;

}
