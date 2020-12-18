package com.wsl.shoppingkill.serviceImpl;

import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.RegexUtils;
import com.wsl.shoppingkill.component.jwt.JwtComponent;
import com.wsl.shoppingkill.mapper.LoginMapper;
import com.wsl.shoppingkill.obj.bo.UserBO;
import com.wsl.shoppingkill.obj.constant.JwtEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.param.UserParam;
import com.wsl.shoppingkill.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @Value("${req.doMainUrl}")
    private String doMainUrl;

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
        UserBO userBO = loginMapper.login(userParam);
        if (Objects.isNull(userBO)){
            return false;
        }
        String token = jwtComponent.getToken(userBO);
        setRes(response,token,userBO);
        redisTemplate.opsForValue().set(RedisEnum.VERIFY_TOKEN+token,userBO,redisToken, TimeUnit.SECONDS);
        return true;
    }

    @Override
    @MyLog(detail = "管理员登录",grade = LoggerEnum.INFO)
    public boolean exit(HttpServletResponse response, HttpServletRequest request) {

        String header = request.getHeader(JwtEnum.AUTH_HEADER_KEY);
        if (StringUtils.isEmpty(header)) {
            for (Cookie cookie : request.getCookies()) {
                if (JwtEnum.TOKEN.equals(cookie.getName())){
                    header = cookie.getValue();
                }
            }
        }
        //解析请求头（防止伪造token，token内容以"shoppingkill "作为开头
        if (StringUtils.isNotEmpty(header) && header.startsWith(JwtEnum.TOKEN_PREFIX)) {
            String token = header.substring(JwtEnum.TOKEN_PREFIX.length());
            response.setHeader(JwtEnum.AUTH_HEADER_KEY,null);
            Cookie name = new Cookie("name", null);
            name.setPath("/");
            name.setDomain(doMainUrl);
            response.addCookie(name);
            Cookie img = new Cookie("img", null);
            img.setPath("/");
            img.setDomain(doMainUrl);
            response.addCookie(img);
            Cookie token1 = new Cookie("token",null);
            token1.setMaxAge(redisToken.intValue());
            token1.setPath("/");
            token1.setDomain(doMainUrl);
            response.addCookie(token1);
            redisTemplate.delete(RedisEnum.VERIFY_TOKEN+token);
            return true;
        }
        return false;
    }

    @Override
    public boolean experienceLogin(HttpServletResponse response, UserParam userParam) throws UnsupportedEncodingException {
        UserBO userBO = redisTemplate.opsForValue().get(RedisEnum.EXPERIENCE_LOGIN + userParam.getName());
        if (!Objects.isNull(userBO)){
            String token = jwtComponent.getToken(userBO);
            setRes(response,token,userBO);
            return true;
        }
        return false;
    }



    private void setRes(HttpServletResponse response,String token,UserBO userBO) throws UnsupportedEncodingException {
        response.setHeader(JwtEnum.AUTH_HEADER_KEY, JwtEnum.TOKEN_PREFIX+token);
        Cookie name = new Cookie("name", URLEncoder.encode(userBO.getName(), "UTF-8"));
        name.setPath("/");
        name.setDomain(doMainUrl);
        response.addCookie(name);
        Cookie img = new Cookie("img", userBO.getUrl());
        img.setPath("/");
        img.setDomain(doMainUrl);
        response.addCookie(img);
        Cookie token1 = new Cookie("token", JwtEnum.TOKEN_PREFIX + token);
        token1.setMaxAge(redisToken.intValue());
        token1.setPath("/");
        token1.setDomain(doMainUrl);
        response.addCookie(token1);
    }
}
