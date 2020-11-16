package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.service.SubscriptionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/16-14:31
 **/
@RestController
@RequestMapping("/admin")
public class SubscriptionHistoryController {

    @Resource
    private SubscriptionHistoryService subscriptionHistoryService;

    @GetMapping("/getAllSubscription/v1")
    public Result<IPage<SubscriptionHistory>> getAllSubscription(@RequestParam(defaultValue = "10")Integer size, @RequestParam(defaultValue = "1") Integer current){
        return Result.success(subscriptionHistoryService.getAllSubscriptionHistory(current,size));
    }

}
