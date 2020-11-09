package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.obj.vo.LoggersVO;
import com.wsl.shoppingKill.service.admin.LoggersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/9-16:31
 **/
@RestController
@RequestMapping("/admin")
public class LoggersController {

    @Resource
    private LoggersService loggersService;

    /**
     * 根据角色获取log日志列表
     * @param page : num :type
     * @return Page
     * @author wangshilei
     * @date 2020/11/9 16:40
     **/
    @GetMapping("/getAllLoggerByType/v1")
    public Result<IPage<LoggersVO>> getAllLoggerByType(@RequestParam(defaultValue = "0") Long page,
                                                      @RequestParam(defaultValue = "10") Long num,
                                                      @RequestParam(defaultValue = "0") Integer type){
        return Result.success(loggersService.getLogListLimit(page,num,type));
    }
}
