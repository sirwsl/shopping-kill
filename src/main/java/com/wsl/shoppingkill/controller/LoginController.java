package com.wsl.shoppingkill.controller;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.VerifyComponent;
import com.wsl.shoppingkill.domain.Experience;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.param.UserParam;
import com.wsl.shoppingkill.service.ExperienceService;
import com.wsl.shoppingkill.service.LoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    private ExperienceService experienceService;
    /**
     * 用户登录接口
     *
     * @param response  :
     * @param request   :
     * @param userParam :
     * @return Result<java.lang.String>
     * @author wangShilei
     * @date 2020/11/24 11:48
     */
    @PostMapping("/user/v1")
    public Result<String> userLogin(HttpServletResponse response, HttpServletRequest request, @Valid UserParam userParam) throws UnsupportedEncodingException {

        if (!verifyComponent.imgVerifyCode(userParam.getCode(), request)) {
            return Result.error("error", "验证码不正确");
        }

        userParam.setType(BaseEnum.USER);
        if (loginService.login(response, userParam)) {
            return Result.success("登录成功");
        }
        return Result.error("error", "密码错误");

    }

    /**
     * 管理员登录接口
     *
     * @param response  :
     * @param request   :
     * @param userParam :
     * @return Result<java.lang.String>
     * @author wangShilei
     * @date 2020/11/24 11:48
     */
    @PostMapping("/admin/v1")
    public Result<String> adminLogin(@Valid UserParam userParam, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {

        if (!verifyComponent.imgVerifyCode(userParam.getCode(), request)) {
            return Result.error("error", "验证码不正确");
        }

        if (loginService.experienceLogin(response,userParam)){
            return Result.success("登录成功");
        }
        userParam.setType(BaseEnum.ADMIN);
        if (loginService.login(response, userParam)) {
            return Result.success("登录成功");
        }
        return Result.error("error", "密码错误");
    }

    /**
     * 退出系统
     * @author wangShilei
     * @date 2020/12/3 14:08
     * @param response :
     * @param request :
     * @return Result<java.lang.Boolean>
     */
    @GetMapping("/exit/v1")
    public Result<Boolean> exitLogin(HttpServletResponse response, HttpServletRequest request){
        return Result.success(loginService.exit(response,request));
    }

    @GetMapping("/getExperience/v1")
    @MyLog(detail = "用户注册",grade = LoggerEnum.INFO)
    public Result<String> getExperience(@Validated boolean check,Experience experience,HttpServletRequest request){
        if (verifyComponent.checkLogin(experience.getPhone())){
            return Result.error("error","您本周内已经体验过，如需再次体验，请联系管理员！ （管理员QQ:2572396933）");
        }
        if (!check){
            return Result.error("error","请勾选体验用户协议");
        }
        return Result.success(experienceService.getExp(experience,request));
    }
}
