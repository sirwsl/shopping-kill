package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.BaseEnum;
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


    /**
     * 消息推送，短信-邮件走同一个
     * @author : WangShiLei
     * @date : 2020/11/15 5:36 下午
     * @param subscriptionHistory:
     * @return com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     **/
    @PostMapping("/newsPush")
    public Result<Boolean> send(SubscriptionHistory subscriptionHistory){
        if (subscriptionHistory.getType().equals(BaseEnum.PHONE)){
            if (subscriptionHistory.getTitle().length()>12){
                return Result.error("error","短信模板消息标题必须小于12字");
            }
            if (subscriptionHistory.getDetail().length()>12){
                return Result.error("error","主体内容只需要填写时间，小于12字");
            }
        }
        boolean flag = subscriptionHistoryService.sendNewsSubscription(subscriptionHistory);
        if (flag){
            return Result.success();
        }
        return Result.error();
    }

}
