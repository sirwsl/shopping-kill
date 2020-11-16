package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.domain.Subscriber;
import com.wsl.shoppingKill.service.SubscriberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/** 订阅者管理
 * @author WangShilei
 * @date 2020/11/16-15:37
 **/
@RestController
@RequestMapping("/admin")
public class SubscriberController {

    @Resource
    private SubscriberService subscriberService;
    /**
     * 获取订阅者 短信
     * @param size :
     * @param current :
     * @return null
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    @GetMapping("/getSubscriberBySms/v1")
    public Result<IPage<Subscriber>> getSubscriberBySms(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size){
        return Result.success(subscriberService.getSubscriber(size,current, BaseEnum.PHONE));
    }

    /**
     * 获取订阅者 邮件
     * @param current :
     * @param size :
     * @return null
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    @GetMapping("/getSubscriberByEmail/v1")
    public Result<IPage<Subscriber>> getSubscriberByEmail(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size){
        return Result.success(subscriberService.getSubscriber(size,current, BaseEnum.EMAIL));
    }

    /**
     * 添加订阅者
     * @param subscriber :
     * @return Boolean
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    @PostMapping("/addSubscriber/v1")
    public Result<Boolean> addSubscriber(@Valid Subscriber subscriber){
        return Result.success(subscriberService.addSubscriber(subscriber));
    }

    /**
     * 删除订阅者
     * @param number :
     * @return Boolean
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    @DeleteMapping("/delSubscriber/v1")
    public Result<Boolean> delSubscriber(@Valid String number){
        return Result.success(subscriberService.delSubscriber(number));
    }
}
