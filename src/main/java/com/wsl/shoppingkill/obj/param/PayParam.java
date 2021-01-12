package com.wsl.shoppingkill.obj.param;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 支付接口参数
 * @author : WangShiLei
 * @date : 2021/1/1 2:05 下午
 **/

@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PayParam implements Serializable {

    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    /**
     * 支付方式
     */
    @NotNull(message = "支付方式不能为空")
    private Integer payType;

    /**
     * 地址id
     */
    @NotNull(message = "地址不能为空")
    private Long addressId;

    /**
     * 订单备注
     */
    private String detail;

}
