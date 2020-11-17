package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.domain.User;
import com.wsl.shoppingKill.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/** 会员管理
 * TODO:未测试
 * @author : WangShiLei
 * @date : 2020/11/16 9:30 下午
 **/
@RestController
@RequestMapping("/admin")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUserAll/v1")
    public Result<IPage<User>> getUserAll(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size){
        return Result.success(userService.getUserAll(size,current));
    }

    @PutMapping("/updateUserInfo/v1")
    public Result<Boolean> updateUserInfo(User user){
        return Result.success(userService.updateUserInfo(user));
    }

    @DeleteMapping("/delUser/v1")
    public Result<Boolean> delUser(@NotNull(message = "删除id不能为空") Integer id){
        return Result.success(userService.delUserInfo(id));
    }
}
