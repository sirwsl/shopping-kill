package com.wsl.shoppingkill.obj.param;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WangShilei
 * @date 2020/11/23-14:38
 **/

@Data
@AllArgsConstructor
@NotNull
public class UserParam {

    private String phone;

    @NotNull(message = "账号不能为空")
    private String name;

    @NotNull(message = "密码不能未空")
    private String password;

    @NotNull(message = "验证码不能未空")
    private String code;

    /**
     * 1-管理员
     * 2-会员
     */
    private Integer type;
}
