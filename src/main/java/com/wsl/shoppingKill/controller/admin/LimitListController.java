package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.LimitListEnum;
import com.wsl.shoppingKill.obj.param.LimitListParam;
import com.wsl.shoppingKill.service.admin.LimitListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : WangShiLei
 * @date : 2020/11/7 4:29 下午
 **/
@RestController
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
    public Result<Boolean> addLimitListByPhone(LimitListParam limitListParam){
        return Result.success(limitListService.addBlackList(
                limitListParam.setType(LimitListEnum.PHONE)
        ));
    }

    @PostMapping("/addBlackListByIp/v1")
    public Result<Boolean> addLimitListByIp(LimitListParam limitListParam){
        return Result.success(limitListService.addBlackList(
                limitListParam.setType(LimitListEnum.IP)
        ));
    }

    public Result<Boolean> removeLimitList(){
        //TODO 删除
        return Result.success();
    }

}
