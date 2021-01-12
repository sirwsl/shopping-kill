package com.wsl.shoppingkill.obj.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : WangShiLei
 * @date : 2020/12/30 10:01 上午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderDetailVO extends UserOrderVO implements Serializable {

    /**
     * 收件人电话
     */
    private String userPhone;

    /**
     * 收件人地址
     */
    private String userAddress;

    /**
     * 支付类型
     */
    private Integer type;

    /**
     * 商品属性
     */
    private String skuName;


}
