package com.wsl.shoppingKill.controller;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.common.util.JwtTokenUtils;
import com.wsl.shoppingKill.constant.JwtEnum;
import com.wsl.shoppingKill.obj.bo.UserBO;
import com.wsl.shoppingKill.obj.param.UserParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @author WangShilei
 * @date 2020/11/23-14:22
 **/

@RestController
public class LoginController {


    @GetMapping("/login")
    public Result<String> login(HttpServletResponse response, UserParam userParam) throws UnsupportedEncodingException {
        UserBO user = new UserBO();
        user.setName("王世磊").setId(12025L).setUrl("https://static.wslhome.top/user/test.jpg").setFlag(0);
        String token = JwtTokenUtils.getToken(user);
        response.setHeader(JwtEnum.AUTH_HEADER_KEY, JwtEnum.TOKEN_PREFIX+token);
        response.addCookie(new Cookie("name", URLEncoder.encode(user.getName(),"UTF-8")));
        response.addCookie(new Cookie("img",user.getUrl()));

        return Result.success("登录成功");
    }
}
