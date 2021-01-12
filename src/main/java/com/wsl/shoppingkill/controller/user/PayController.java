package com.wsl.shoppingkill.controller.user;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.param.PayParam;
import com.wsl.shoppingkill.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 支付接口
 * @author : WangShiLei
 * @date : 2021/1/1 2:03 下午
 **/

@RestController
@RequestMapping("/user")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/pay/v1")
    public Result<String> userPay(@Valid PayParam payParam){
        if (payService.pay(payParam)){
            return Result.success("支付成功");
        }else {
            return Result.error("error","支付失败");
        }
    }
}
