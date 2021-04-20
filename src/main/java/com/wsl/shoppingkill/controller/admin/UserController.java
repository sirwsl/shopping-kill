package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 会员管理
 * TODO:未测试
 *
 * @author : WangShiLei
 * @date : 2020/11/16 9:30 下午
 **/
@RestController
@RequestMapping("/admin")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUserAll/v1")
    public Result<IPage<User>> getUserAll(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(userService.getUserAll(size, current));
    }

    @PutMapping("/updateUserInfo/v1")
    public Result<Boolean> updateUserInfo(User user) {
        return Result.success(userService.updateUserInfo(user));
    }

    @DeleteMapping("/delUser/v1")
    public Result<Boolean> delUser(Long id) {
        if (id == null || id == 0) {
            return Result.error("error", "删除id不能为空");
        }
        return Result.success(userService.delUserInfo(id));
    }
}
