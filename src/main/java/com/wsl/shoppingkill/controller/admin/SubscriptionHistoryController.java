package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.SubscriptionHistory;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.param.PageTimeParam;
import com.wsl.shoppingkill.service.SubscriptionHistoryService;
import org.springframework.web.bind.annotation.*;

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
