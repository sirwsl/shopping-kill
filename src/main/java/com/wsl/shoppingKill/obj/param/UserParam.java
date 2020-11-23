package com.wsl.shoppingKill.obj.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author WangShilei
 * @date 2020/11/23-14:38
 **/

@Data
@AllArgsConstructor
@NotNull
public class UserParam {

    @NotNull(message = "用户名不能未空")
    private String name;

    @NotNull(message = "密码不能未空")
    @Length(min = 6,message = "密码不能少于6位")
    private String password;

    @NotNull(message = "验证码不能未空")
    private String code;
}
