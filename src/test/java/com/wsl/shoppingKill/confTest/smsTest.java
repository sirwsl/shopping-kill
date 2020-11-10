package com.wsl.shoppingKill.confTest;

import com.wsl.shoppingKill.component.sms.SmsValidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class smsTest {

    @Resource
    SmsValidate smsValidate;

    @Test
    public void smsTest1(){
        smsValidate.getCode("18314263373");
        System.out.println("短信已发送");
    }
}
