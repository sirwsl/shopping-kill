package com.wsl.shoppingKill.confTest;


import com.wsl.shoppingKill.common.util.SmsValidat;
import org.junit.jupiter.api.Test;

public class smsTest {

    @Test
    public void smsTest1(){
        SmsValidat smsValidat = new SmsValidat();
        smsValidat.getCode("18314263373");
        System.out.println("短信已发送");
    }
}
