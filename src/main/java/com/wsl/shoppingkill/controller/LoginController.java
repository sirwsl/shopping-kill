package com.wsl.shoppingkill.controller;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.RegexUtils;
import com.wsl.shoppingkill.component.VerifyComponent;
import com.wsl.shoppingkill.domain.Experience;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.param.UserParam;
import com.wsl.shoppingkill.service.ExperienceService;
import com.wsl.shoppingkill.service.LoginService;
import com.wsl.shoppingkill.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private UserService userService;
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

    @PostMapping("/addUser/v1")
    public Result<String> addUser(String codes,@Valid User user,HttpServletRequest request){
        if (StringUtils.isBlank(codes)){
            return Result.error("error", "验证码不能为空");
        }
        if (!verifyComponent.imgVerifyCode(codes, request)) {
            return Result.error("error", "验证码不正确");
        }
        if(!RegexUtils.checkEmail(user.getEmail())){
            return Result.error("error","邮箱校验失败，请更换邮箱重试");
        }
        if (!RegexUtils.checkMobile(user.getPhone())){
            return Result.error("error","手机号校验失败，请更换手机号重试");
        }

        if(!RegexUtils.checkIdCard(user.getIdCard())){
            return Result.error("error","身份证号校验失败，请认真填写身份证号");
        }

        if (userService.addUser(user)){
            return Result.success("恭喜您注册成功！请尽情使用该系统进行购物");
        }
        return Result.error("error","注册失败，请您稍后再试");
    }
}
