package com.wsl.shoppingkill.obj.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 订单确认参数
 * @author : WangShiLei
 * @date : 2021/1/2 1:46 上午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AddOrderParam {

    /**
     * Sku id
     */
    @NotNull(message = "商品编号不能为空")
    private Long skuId;

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @Min(value = 1,message = "购买数量最低为1")
    private Integer num;

    private Long userId;


}
