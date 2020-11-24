package com.wsl.shoppingKill.component.sms;

import com.alibaba.fastjson.JSONObject;
import com.wsl.shoppingKill.constant.RedisEnum;
import com.wsl.shoppingKill.constant.SmsEnum;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    @Value("${verify.phoneCodeTimeOut}")
    Long phoneCodeTimeOut;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送短信验证码
     * @param phone :
     * @return sendVerification
     * @author wangshilei
     * @date 2020/11/13 17:57
     **/
    public boolean sendVerification(String phone){
        //随机生成验证码
        String code = String.valueOf(new Random().nextInt(999999));
        //发送内容
        String[] templateParams = new String[2];
        templateParams[0] = code;
        templateParams[1] = "5分钟";
        boolean send = send(SmsEnum.VERIFICATION_CODE.getCode(), templateParams, phone);
        if (send){
            stringRedisTemplate.opsForValue().set(RedisEnum.VERIFY_PHONE_CODE+phone,code, phoneCodeTimeOut,TimeUnit.SECONDS);
            return true;
        }else {
            return false;
        }
    }


    /*-------------原生禁用----------------**/
    /**
     * @param phone ：手机号
     * @param text :内容
     * @param templateCode :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/13 17:59
     **/
    public boolean send(Integer templateCode,String[] text,String phone){
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        Map<String, Object> params = new HashMap<>(8);
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
