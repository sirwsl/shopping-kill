package com.wsl.shoppingKill.controller;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.obj.param.UserParam;
import com.wsl.shoppingKill.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;


/**
 * @author WangShilei
 * @date 2020/11/23-14:22
 **/

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;



    @GetMapping("/login/v1")

    public Result<String> userLogin(HttpServletResponse response, @Valid UserParam userParam) throws UnsupportedEncodingException {
        userParam.setType(BaseEnum.ADMIN);
        if (loginService.login(response,userParam)){
            return Result.success("登录成功");
        }
        return Result.error("error","登录失败");

    }

    @GetMapping("/adminLogin/v1")
    public Result<String> adminLogin(HttpServletResponse response, @Valid UserParam userParam) throws UnsupportedEncodingException {
        userParam.setType(BaseEnum.USER);
        if (loginService.login(response,userParam)){
            return Result.success("登录成功");
        }
        return Result.error("error","登录失败");
    }
}
