package com.wsl.shoppingKill.controller;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.common.util.RegexUtils;
import com.wsl.shoppingKill.component.VerifyComponent;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 验证
 * @author WangShilei
 * @date 2020/11/13-18:01
 **/

@RestController
@Slf4j
@RequestMapping("/verify")
public class VerifyController {

    @Resource
    private SmsComponent smsComponent;

    @Resource
    private VerifyComponent verifyComponent;



    /**
     * 生成验证码
     * @param httpServletResponse:获取方式
     * @throws Exception:验证码生成异常
     */
    @RequestMapping("/getJpg/v1")
    public void getKaptCha1(HttpServletResponse httpServletResponse, HttpServletRequest request)
            throws Exception {
        verifyComponent.getKaptCha(httpServletResponse, request);
    }


    /**
     * 发送短信验证码
     * @param phone :
     * @return sendVerification
     * @author wangshilei
     * @date 2020/11/13 17:57
     **/
    @RequestMapping("/getCode/v1")
    public Result<Boolean> sendVerification(String phone){
        //查看账号是不是手机号
        if(!RegexUtils.checkMobile(phone)){
        return Result.error("error","手机号不正确");
        }
        try {
            return Result.success(smsComponent.sendVerification(phone));
        }catch (Exception e){
            return Result.error("error","验证码发送失败，请稍后再试");
        }
    }
}
