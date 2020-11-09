package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.service.AddressService;
import com.wsl.shoppingKill.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/** 管理员api
 * @author WangShilei
 * @date 2020/11/9-11:24
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    /**
     * 添加一个管理员
     * com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     * @author wangshilei
     * @date 2020/11/9 11:33
     **/
    @PostMapping("/addAdmin/v1")
    public Result<Boolean> addAdmin(@Valid Admin admin){

        return Result.success(adminService.addAdmin(admin));
    }


    /**
     * 更新一个管理员
     * @param admin :
     * @return Result<Boolean>
     * @author wangshilei
     * @date 2020/11/9 11:34
     **/
    @PutMapping("/updateAdmin/v1")
    public Result<Boolean> updateAdmin(@Valid Admin admin){
        return Result.success(adminService.updateAdmin(admin));
    }

    /**
     * 根据id删除一个管理员
     * @param id :
     * @return Result<Boolean>
     * @author wangshilei
     * @date 2020/11/9 11:35
     **/
    @DeleteMapping("/delAdmin/v1")
    public Result<Boolean> delAdmin(@NotNull(message = "id不能为空") Long id){
        return Result.success(adminService.delAdmin(id));
    }

    /**
     * 获取一个管理员列表
     * @return Result<List<Admin>>
     * @author wangshilei
     * @date 2020/11/9 11:35
     **/
    @GetMapping("/getAdmin/v1")
    public Result<List<Admin>> getAdmin(){
        return Result.success(adminService.getAdminList());
    }

}