package com.wsl.shoppingKill.controller.verify;

import com.alibaba.fastjson.JSONObject;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import com.wsl.shoppingKill.constant.SmsEnum;

import javax.annotation.Resource;
import java.util.Random;

/** 验证
 * @author WangShilei
 * @date 2020/11/13-18:01
 **/
public class VerificationController {

    @Resource
    private SmsComponent smsComponent;

    /**
     * TODO:缺少加入redis
     * 发送短信验证码
     * @param phone :
     * @return sendVerification
     * @author wangshilei
     * @date 2020/11/13 17:57
     **/
    public String sendVerification(String phone){
        JSONObject json = new JSONObject();
        //随机生成验证码
        String code = String.valueOf(new Random().nextInt(999999));
        //发送内容
        String[] templateParams = new String[2];
        templateParams[0] = code;
        templateParams[1] = "5分钟";
        boolean send = smsComponent.send(SmsEnum.VERIFICATION_CODE.getCode(), templateParams, phone);
        if (send){
            return code;
        }else {
            return null;
        }
    }
}
