package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.obj.param.PageTimeParam;
import com.wsl.shoppingKill.service.SubscriptionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author WangShilei
 * @date 2020/11/16-14:31
 **/
@RestController
@RequestMapping("/admin")
public class SubscriptionHistoryController {

    @Resource
    private SubscriptionHistoryService subscriptionHistoryService;


    /**
     * 获取全部订阅历史
     * @param size : 当前页条数
     * @param current : 页大小
     * @return Result<IPage<SubscriptionHistory>>
     * @author wangshilei
     * @date 2020/11/16 15:02
     **/
    @GetMapping("/getAllSubscription/v1")
    public Result<IPage<SubscriptionHistory>> getAllSubscription(@RequestParam(defaultValue = "10")Integer size, @RequestParam(defaultValue = "1") Integer current){
        return Result.success(subscriptionHistoryService.getAllSubscriptionHistory(current,size));
    }

    /**
     * 按照时间范围获取订阅历史
     * @param pageTimeParam :
     * @return Result<IPage<SubscriptionHistory>>
     * @author wangshilei
     * @date 2020/11/16 15:09
     **/
    @GetMapping("/getSubscriptionByTime/v1")
    public Result<IPage<SubscriptionHistory>> getSubscriptionByTime(@Valid PageTimeParam pageTimeParam){
        if (pageTimeParam.getCurrent() == null || pageTimeParam.getCurrent() == 0 ){
            pageTimeParam.setCurrent(1);
        }
        if (pageTimeParam.getSize() == null || pageTimeParam.getSize() == 0){
            pageTimeParam.setSize(10);
        }
        return Result.success(subscriptionHistoryService.getSubscriptionHistoryByTime(pageTimeParam));
    }
}
