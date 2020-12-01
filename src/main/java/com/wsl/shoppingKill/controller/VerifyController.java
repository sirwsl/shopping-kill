package com.wsl.shoppingKill.controller;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.common.util.RegexUtils;
import com.wsl.shoppingKill.component.VerifyComponent;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import com.wsl.shoppingKill.obj.constant.RedisEnum;
import com.wsl.shoppingKill.obj.constant.SmsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${verify.phoneCodeTimeOut}")
    Long phoneCodeTimeOut;

    /**
     * 生成验证码
     * @param httpServletResponse:获取方式
     * @throws Exception:验证码生成异常
     */
    @RequestMapping("/getJpg/v1")
    public void getKatChap1(HttpServletResponse httpServletResponse, HttpServletRequest request)
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
        if (phone.isEmpty()){
            return Result.error("error","手机号不能为空");
        }
        if(!RegexUtils.checkMobile(phone)){
        return Result.error("error","手机号不正确");
        }
        //随机生成验证码
        String code = String.valueOf(new Random().nextInt(999999));
        List<String> description = new ArrayList<>(4);
        description.add(code);
        description.add("5分钟");
        boolean send = smsComponent.send(SmsEnum.VERIFICATION_CODE.getCode(), description, phone);
        if (send){
            stringRedisTemplate.opsForValue().set(RedisEnum.VERIFY_PHONE_CODE+phone,code, phoneCodeTimeOut, TimeUnit.SECONDS);
            return Result.success(true);
        }else {
            return Result.error(false);
        }
    }
}
