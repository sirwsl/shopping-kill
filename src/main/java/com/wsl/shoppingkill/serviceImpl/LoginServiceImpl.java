package com.wsl.shoppingkill.serviceImpl;

import com.wsl.shoppingkill.common.util.RegexUtils;
import com.wsl.shoppingkill.component.jwt.JwtComponent;
import com.wsl.shoppingkill.mapper.LoginMapper;
import com.wsl.shoppingkill.obj.bo.UserBO;
import com.wsl.shoppingkill.obj.constant.JwtEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.param.UserParam;
import com.wsl.shoppingkill.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : WangShiLei
 * @date : 2020/11/23 11:00 下午
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${jwt.redisToken}")
    private Long redisToken;

 /*   @Value("${req.doMainUrl}")
    private String doMainUrl;*/

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private JwtComponent jwtComponent;

    @Resource
    private RedisTemplate<String,UserBO> redisTemplate;

    @Override
    public boolean login(HttpServletResponse response, UserParam userParam) throws UnsupportedEncodingException {
        //查看账号是不是手机号
        if(RegexUtils.checkMobile(userParam.getName())){
            userParam.setPhone(userParam.getName());
            userParam.setName(null);
        }
        //登录
        UserBO userBO = loginMapper.login(userParam).setFlag(userParam.getType());
        if (Objects.isNull(userBO)){
            return false;
        }
        //设置头
        userBO.setFlag(userParam.getType());
        String token = jwtComponent.getToken(userBO);
        response.setHeader(JwtEnum.AUTH_HEADER_KEY, JwtEnum.TOKEN_PREFIX+token);
        Cookie name = new Cookie("name", URLEncoder.encode(userBO.getName(), "UTF-8"));
        name.setPath("/");
        //name.setDomain(doMainUrl);
        response.addCookie(name);
        Cookie img = new Cookie("img", userBO.getUrl());
        img.setPath("/");
        //img.setDomain(doMainUrl);
        response.addCookie(img);
        Cookie token1 = new Cookie("token", JwtEnum.TOKEN_PREFIX + token);
        token1.setMaxAge(redisToken.intValue());
        token1.setPath("/");
        //token1.setDomain(doMainUrl);
        response.addCookie(token1);
        redisTemplate.opsForValue().set(RedisEnum.VERIFY_TOKEN+token,userBO,redisToken, TimeUnit.SECONDS);
        return true;
    }
}
