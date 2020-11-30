package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.common.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingKill.obj.constant.BaseEnum;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/** 管理员api
 * @author WangShilei
 * @date 2020/11/9-11:24
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AbstractCurrentRequestComponent component;
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
        if (!component.getCurrentUser().getFlag().equals(BaseEnum.ADMIN)){
            return Result.error("error","只有超级管理员有权限");
        }
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
        if (!component.getCurrentUser().getId().equals(admin.getId())){
            return Result.error("error","只有本人才能修改");
        }
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
    public Result<Boolean> delAdmin(Long id){
        if (id == null || id == 0){
            return Result.error("error","id不能为空");
        }
        if (!component.getCurrentUser().getFlag().equals(BaseEnum.ADMIN)){
            return Result.error("error","只有超级管理员有权限");
        }
        return Result.success(adminService.delAdmin(id));
    }

    /**
     * 获取一个管理员列表
     * @return Result<List<Admin>>
     * @author wangshilei
     * @date 2020/11/9 11:35
     **/
    @GetMapping("/getAdminList/v1")
    public Result<List<Admin>> getAdminList(){
        return Result.success(adminService.getAdminList());
    }


    /**
     * 获取当前用户信息
     * @return Admin
     * @author wangshilei
     * @date 2020/11/9 17:53
     **/
    @GetMapping("/getAdmin/v1")
    public Result<Admin> getAdmin(){
        Long id = component.getCurrentUser().getId();
        if(id==null || id==0){
            return Result.error("error","登录异常，请重新尝试修改");
        }
        Admin admin = adminService.getById(id);
        if (Objects.isNull(admin)){
            return Result.error("error","当前登录id异常");
        }
        return Result.success(admin);
    }

}
