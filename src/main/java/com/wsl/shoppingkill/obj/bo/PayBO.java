package com.wsl.shoppingkill.obj.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.catalina.valves.RemoteIpValve;

import java.io.Serializable;

/**
 * 支付中间对象
 * @author : WangShiLei
 * @date : 2021/1/1 4:14 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PayBO implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

}
