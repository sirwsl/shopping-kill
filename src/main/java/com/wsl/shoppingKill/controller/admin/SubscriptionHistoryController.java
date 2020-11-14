package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.service.SubscriptionHistoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : WangShiLei
 * @date : 2020/11/13 11:44 下午
 **/
@RestController
@RequestMapping("/admin")
public class SubscriptionHistoryController {

    @Resource
    private SubscriptionHistoryService subscriptionHistoryService;


    @PostMapping("/send")
    public Result<Boolean> send(SubscriptionHistory subscriptionHistory){
        boolean flag = subscriptionHistoryService.sendNewsSubscription(subscriptionHistory);
        if (flag){
            return Result.success();
        }
        return Result.error();
    }

}
