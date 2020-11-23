package com.wsl.shoppingKill.component.JwtToken;

import com.wsl.shoppingKill.obj.bo.UserBO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
class JwtTokenComponentTest {

    @Resource
    private JwtTokenComponent jwtTokenComponent;

    @Test
    void generatorToken() {
        UserBO user = new UserBO();
        user.setName("王世磊").setId(12025L).setUrl("https:static.wslhome.top/user/test.jpg").setFlag(0);
        String s = jwtTokenComponent.generatorToken(user,1);
        System.err.println(s);
    }

    @Test
    void stringInfoFromToken() {
        UserBO userBO = jwtTokenComponent.stringInfoFromToken("eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnk6dXNlcjppZCI6MTIwMjUsInZlcmlmeTp1c2VyOm5hbWUiOiLnjovkuJbno4oiLCJ2ZXJpZnk6dXNlcjp1cmwiOiJodHRwczpzdGF0aWMud3NsaG9tZS50b3AvdXNlci90ZXN0LmpwZyIsInZlcmlmeTp1c2VyOmZsYWciOjAsImV4cCI6MTYwNjEwMTkxMH0.Iv1WOGFGLHENv-91Z8Gc4Z9M_QgU-83Eka34louQlO4");
        System.err.println(userBO);
    }
}