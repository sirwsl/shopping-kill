package com.wsl.shoppingkill.obj.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 售后记录VO
 * @author wangshilei
 * @date 2020/11/4 16:39
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AfterSalesParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;

    /**
    * 订单id
    */
    private String orderId;

    /**
     * 售后类型
     */
    private String type;

    /**
    * 是否解决（0-未解决 1-已解决  默认0）
    */
    private Boolean result;


}