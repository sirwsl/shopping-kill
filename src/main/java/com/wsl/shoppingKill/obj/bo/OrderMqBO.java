package com.wsl.shoppingKill.obj.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author WangShilei
 * @date 2020/11/26-10:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class OrderMqBO implements Serializable {

    /**
     * 顾客邮箱
     */
    private String email;
    /**
     * 顾客手机
     */
    private String phone;
    /**
     * 顾客昵称
     */
    private String userName;

    /**
     * 物品名称
     */
    private String goodsName;
}
