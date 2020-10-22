package com.wsl.shoppingKill.confTest;


import com.wsl.shoppingKill.util.SmsValidat;
import org.junit.jupiter.api.Test;

public class smsTest {

    @Test
    public void smsTest(){
        SmsValidat smsValidat = new SmsValidat();
        smsValidat.getCode("18314263373");
        System.out.println("短信已发送");
    }
}
