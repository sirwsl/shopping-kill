package com.wsl.shoppingKill.component.sms;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 短信模板组件
 * @author WangShilei
 * @date 2020/11/13-17:36
 **/

@Component
@Slf4j
public class SmsComponent {


    @Value("${zzy.apiUrl}")
    String apiUrl;
    @Value("${zzy.appSecret}")
    String appSecret;
    @Value("${zzy.appId}")
    String appId;

    /**
     * @param phone ：手机号
     * @param list :内容
     * @param templateCode :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/13 17:59
     **/
    public boolean send(Integer templateCode, List<String> list, String phone){
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        Map<String, Object> params = new HashMap<>(8);
        String[] text = list.toArray(new String[0]);
        params.put("number", phone);
        params.put("templateId", templateCode);
        params.put("templateParams", text);
        try {
            String result = client.send(params);
            String key = "code";
            if (JSONObject.parseObject(result).getIntValue(key)==0){
                return true;
            }

        }catch (Exception e){
            log.error("短信消息发送异常：{}",e.getMessage());
        }
        return false;
    }
}
