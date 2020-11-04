package com.wsl.shoppingKill.common.util;
import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 短信验证工具实现
 * @author: wangShilei
 * @create: 2020-05-26 21:54
 **/

@Component
@Slf4j
public class SmsValidat {

    public JSONObject getCode(String memPhone){
        JSONObject json = new JSONObject();
        try {
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机

            String apiUrl = "https://sms_developer.zhenzikj.com";
            String appSecret = "MDAxNDZiNGMtNTYwNy00ZTcwLWEyNTItMGY1OThmYmNjZGM2";
            String appId = "105924";
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            Map<String, Object> params = new HashMap<>(2);
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