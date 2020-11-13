package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.LimitList;
import com.wsl.shoppingKill.obj.param.LimitListParam;
import com.wsl.shoppingKill.service.LimitListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

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
     * 获取黑名单手机号列表
     * @author : WangShiLei
     * @date : 2020/11/8 12:28 下午
     * @return Result<Page<LimitListParam>>
     **/
    @GetMapping("/getBlackListForPhone/v1")
    public Result<IPage<LimitList>> getLimitListForPhone(Integer page, Integer num){
        return Result.success(limitListService.getBlackListForPhone(page,num));
    }

    /**
     * 获取黑名单IP列表
     * @author : WangShiLei
     * @date : 2020/11/8 12:28 下午
     * @return Result<Page<LimitListParam>>
     **/
    @GetMapping("/getBlackListForIp/v1")
    public Result<IPage<LimitList>> getLimitListForIp(Integer page, Integer num){
        return Result.success(limitListService.getBlackListForIp(page,num));
    }

    /**
     * 更具手机号进行查询
     * @author : WangShiLei
     * @date : 2020/11/8 5:28 下午
     * @param num:
     * @return com.wsl.shoppingKill.common.Result<java.util.List<com.wsl.shoppingKill.domain.LimitList>>
     **/
    @GetMapping("/selectBlackListByPhone/v1")
    public Result<List<LimitList>> getLimitListByPhone( String num){
        if (StringUtils.isEmpty(num)){
            return Result.error("error","手机号不能为空");
        }
        return Result.success(limitListService.getBlackListByNumber(num, BaseEnum.PHONE));
    }

    /**
     * 更具IP查询
     * @author : WangShiLei
     * @date : 2020/11/8 5:28 下午
     * @param num:
     * @return com.wsl.shoppingKill.common.Result<java.util.List<com.wsl.shoppingKill.domain.LimitList>>
     **/
    @GetMapping("/selectBlackListByIp/v1")
    public Result<List<LimitList>> getLimitListByIp( String num){
        if (StringUtils.isEmpty(num)){
            return Result.error("error","IP不能为空");
        }
        return Result.success(limitListService.getBlackListByNumber(num,BaseEnum.IP));
    }

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
                limitListParam.setType(BaseEnum.PHONE)
        ));
    }

    @PostMapping("/addBlackListByIp/v1")
    public Result<Boolean> addLimitListByIp(@Valid LimitListParam limitListParam){
        try {
            limitListService.addBlackList(
                    limitListParam.setType(BaseEnum.IP)
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
    @MyLog(detail = "移除黑名单",grade = LoggerEnum.SERIOUS,value = "#id")
    public Result<Boolean> removeLimitListById(Long id){
        return Result.success(limitListService.removeById(id));
    }

    @DeleteMapping("/delBlackListByIds/v1")
    @MyLog(detail = "移除黑名单",grade = LoggerEnum.SERIOUS,value = "#id")
    public Result<Boolean> removeLimitListByIds(Integer[] ids){
        return Result.success(limitListService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 更新黑名单列表
     * @author : WangShiLei
     * @date : 2020/11/8 11:06 上午
     * @param limitList: 更新资源
     * @return boolean
     **/
    @PutMapping("/updateBlackListById/v1")
    @MyLog(detail = "更新黑名单",grade = LoggerEnum.SERIOUS,value = "#id")
    public Result<Boolean> updateLimitList(LimitList limitList){
        return Result.success(limitListService.updateById(limitList));
    }
}
