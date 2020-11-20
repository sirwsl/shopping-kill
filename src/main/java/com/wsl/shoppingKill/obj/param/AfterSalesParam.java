package com.wsl.shoppingKill.obj.param;

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
     * 用户账户
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
    * 是否解决（0-未解决 1-已解决  默认0）
    */
    private Boolean result;


}