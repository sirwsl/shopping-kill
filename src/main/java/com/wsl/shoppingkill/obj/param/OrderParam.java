package com.wsl.shoppingkill.obj.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangShilei
 * @date 2020/11/25-15:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderParam {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 订单id
     */
    private String id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 物品id
     */
    private Long goodsId;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 用户昵称
     */
    private String nickName;



}
