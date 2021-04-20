package com.wsl.shoppingkill.controller.user;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.common.util.RegexUtils;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : WangShiLei
 * @date : 2020/12/29 10:36 上午
 **/
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserService userService;

    /**
     * 获取当前登录者的信息
     *
     * @return Result<User>
     * @author wangShilei
     * @date 2020/12/29 10:37 上午
     */
    @GetMapping("/getUserInfo/v1")
    public Result<User> getUserInfo() {
        return Result.success(userService.getUserInfo());
    }

    /**
     * 用户修改自己的信息
     *
     * @param user :
     * @return com.wsl.shoppingkill.common.Result<java.lang.String>
     * @author wangShilei
     * @date 2020/12/29 10:51 上午
     */
    @PostMapping("updateUserInfo/v1")
    public Result<String> updateUserInfo(User user) {
        if (user == null || user.getId() == null) {
            return Result.error("error", "更新信息不符合规定");
        }
        if (user.getPhone() == null || !RegexUtils.checkMobile(user.getPhone())) {
            return Result.error("error", "手机号码不合法");
        }
        if (user.getEmail() == null || !RegexUtils.checkEmail(user.getEmail())) {
            return Result.error("error", "邮箱地址不合法");
        }
        if (userService.updateUserInfoBySelf(user)) {
            return Result.success("更新信息成功");
        }
        return Result.error("error", "更新信息失败，请稍后再试");
    }

    @PostMapping("/updateImg/v1")
    public Result<String> updateImg(MultipartFile file, HttpServletResponse response) {
        if (file == null || file.isEmpty()) {
            return Result.error("error", "修改头像不能为空");
        }
        if (userService.updateUserImg(file, response)) {
            return Result.success("头像信息修改成功");
        }
        return Result.error("error", "头像信息修改失败，请稍后再试");
    }


    /**
     * 修改密码
     *
     * @param old         :
     * @param newPassword :
     * @return com.wsl.shoppingkill.common.Result<java.lang.String>
     * @author wangShilei
     * @date 2021/1/9 4:58 下午
     */
    @PostMapping("/updatePassword/v1")
    public Result<String> updatePassword(String old, String newPassword, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.isBlank(old) || StringUtils.isBlank(newPassword)) {
            return Result.error("error", "密码不能为空");
        }
        if (newPassword.length() < 6) {
            return Result.error("error", "密码不能少于6位");
        }
        if (userService.updatePassword(old, newPassword, response, request)) {
            return Result.success("密码修改成功");
        }
        return Result.error("error", "密码修改失败");
    }
}
