package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.LimitListEnum;
import com.wsl.shoppingKill.domain.LimitList;
import com.wsl.shoppingKill.obj.param.LimitListParam;
import com.wsl.shoppingKill.service.admin.LimitListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : WangShiLei
 * @date : 2020/11/7 4:29 下午
 **/
@RestController
@RequestMapping("/admin")
public class LimitListController {


    @Resource
    private LimitListService limitListService;

    /**
     *  添加用户黑名单
     * @author : WangShiLei
     * @date : 2020/11/7 5:02 下午
     * @param limitListParam:
     * @return : com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     **/
    @PostMapping("/addBlackListByPhone/v1")
    public Result<Boolean> addLimitListByPhone(@Valid LimitListParam limitListParam){
        return Result.success(limitListService.addBlackList(
                limitListParam.setType(LimitListEnum.PHONE)
        ));
    }

    @PostMapping("/addBlackListByIp/v1")
    public Result<Boolean> addLimitListByIp(@Valid LimitListParam limitListParam){
        try {
            limitListService.addBlackList(
                    limitListParam.setType(LimitListEnum.IP)
            );
        }catch (Exception e){
            return Result.error("error","数据已经存在");
        }
        return Result.success();
    }

    /**
     * 移除黑名单
     * @author : WangShiLei
     * @date : 2020/11/8 10:46 上午
     * @param id:
     * @return com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     **/
    @DeleteMapping("delBlackListById/v1")
    public Result<Boolean> removeLimitList(Long id){
        return Result.success(limitListService.removeById(id));
    }

    /**
     *
     * @author : WangShiLei
     * @date : 2020/11/8 11:06 上午
     * @param limitList: 更新资源
     * @return boolean
     **/
    @PutMapping("/updateBlackListById/v1")
    public Result<Boolean> updateLimitList(LimitList limitList){
        return Result.success(limitListService.updateById(limitList));
    }
}
