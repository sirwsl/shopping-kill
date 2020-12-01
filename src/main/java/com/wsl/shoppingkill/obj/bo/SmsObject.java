package com.wsl.shoppingkill.obj.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/11/25 8:33 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SmsObject implements Serializable {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 模版编号
     */
    private Integer code;

    /**
     * 内容
     */
    private List<String> description;


}
