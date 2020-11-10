package com.wsl.shoppingKill.component.sms;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 短信验证工具实现
 * @author wangShilei
 * 2020-05-26 21:54
 **/

@Component
public class SmsValidate {

    protected static final Logger log = LoggerFactory.getLogger(SmsValidate.class);

    @Value("${zzy.apiUrl}")
    String apiUrl;
    @Value("${zzy.appSecret}")
    String appSecret;
    @Value("${zzy.appId}")
    String appId;


    public JSONObject getCode(String memPhone){
        JSONObject json = new JSONObject();
        try {
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机


            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            Map<String, Object> params = new HashMap<>(8);
            params.put("number", memPhone);
            params.put("templateId", "355");
            String[] templateParams = new String[2];
            templateParams[0] = code;
            templateParams[1] = "5分钟";
            params.put("templateParams", templateParams);
            String result = client.send(params);

            json = JSONObject.parseObject(result);

            //发送短信
            String key = "code";
            if (json.getIntValue(key)==0) {
                json.put("phone", memPhone);
                json.put("code", code);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SMS send error");
        }
        return json;
    }


}