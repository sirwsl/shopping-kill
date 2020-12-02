package com.wsl.shoppingkill.obj.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author WangShilei
 * @date 2020/11/23-14:38
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserParam {

    private String phone;

    @NotNull(message = "账号不能为空")
    @NotBlank(message = "账号不能为空")
    private String name;

    @NotNull(message = "密码不能为空")
    @NotBlank(message ="密码不能为空")
    private String password;

    @NotNull(message = "验证码不能为空")
    @NotBlank(message = "验证码不能为空")
    private String code;

    /**
     * 1-管理员
     * 2-会员
     */
    private Integer type;
}
