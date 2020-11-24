package com.wsl.shoppingKill.controller;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.component.VerifyComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.obj.param.UserParam;
import com.wsl.shoppingKill.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;


/**
 * @author WangShilei
 * @date 2020/11/23-14:22
 **/

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private VerifyComponent verifyComponent;



    /**
     * 用户登录接口
     * @author wangShilei
     * @date 2020/11/24 11:48
     * @param response :
     * @param request :
     * @param userParam :
     * @return Result<java.lang.String>
     */
    @PostMapping("/user/v1")
    public Result<String> userLogin(HttpServletResponse response, HttpServletRequest request, @Valid UserParam userParam) throws UnsupportedEncodingException {
        try{
            if (!verifyComponent.imgVerifyCode(userParam.getCode(), request)) {
                return Result.error("error", "验证码不正确");
            }
        }catch (Exception e){
            return Result.error("error","连接异常，请刷新重试");
        }
        userParam.setType(BaseEnum.USER);
        if (loginService.login(response, userParam)) {
            return Result.success("登录成功");
        }
        return Result.error("error", "登录失败");

    }

    /**
     * 管理员登录接口
     * @author wangShilei
     * @date 2020/11/24 11:48
     * @param response :
     * @param request :
     * @param userParam :
     * @return Result<java.lang.String>
     */
    @PostMapping("/admin/v1")
    public Result<String> adminLogin(HttpServletResponse response, HttpServletRequest request, @Valid UserParam userParam) throws UnsupportedEncodingException {
        try{
            if (!verifyComponent.imgVerifyCode(userParam.getCode(), request)) {
                return Result.error("error", "验证码不正确");
            }
        }catch (Exception e){
            return Result.error("error","连接异常，请刷新重试");
        }
        userParam.setType(BaseEnum.ADMIN);
        if (loginService.login(response, userParam)) {
            return Result.success("登录成功");
        }
        return Result.error("error", "登录失败");
    }
}
