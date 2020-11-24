package com.wsl.shoppingKill.request;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.esotericsoftware.minlog.Log;
import com.wsl.shoppingKill.component.jwt.JwtComponent;
import com.wsl.shoppingKill.constant.JwtEnum;
import com.wsl.shoppingKill.constant.RedisEnum;
import com.wsl.shoppingKill.obj.bo.UserBO;
import com.wsl.shoppingKill.obj.exception.TokenRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author WangShilei
 * @date 2020/11/23-13:58
 **/
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String, UserBO> redisTemplate;

    @Resource
    private JwtComponent jwtComponent;

    @Value("${jwt.newToken}")
    private Long newToken;

    @Value("${jwt.redisToken}")
    private Long redisToken;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头（如果有此请求头，表示token已经签发）
        String header = request.getHeader(JwtEnum.AUTH_HEADER_KEY);
        if (StringUtils.isEmpty(header)) {
            for (Cookie cookie : request.getCookies()) {
                if (JwtEnum.TOKEN.equals(cookie.getName())){
                    header = cookie.getValue();
                }
            }


            if (StringUtils.isEmpty(header)){
                throw new TokenRuntimeException("未登录");
            }

        }
        //解析请求头（防止伪造token，token内容以"shoppingKill "作为开头）

        if (!header.startsWith(JwtEnum.TOKEN_PREFIX)) {
            throw new TokenRuntimeException("Token无效");
        }

        String token = header.substring(JwtEnum.TOKEN_PREFIX.length());
        try {
            if (jwtComponent.getTokenTime(token) < newToken) {
                UserBO userBO = redisTemplate.opsForValue().get(RedisEnum.VERIFY_TOKEN + token);
                if (Objects.isNull(userBO)) {
                    throw new TokenRuntimeException("Token解析异常");
                }
                String newToken = jwtComponent.getToken(userBO);
                response.setHeader(JwtEnum.AUTH_HEADER_KEY, JwtEnum.TOKEN_PREFIX + newToken);
                redisTemplate.delete(RedisEnum.VERIFY_TOKEN + token);
                redisTemplate.opsForValue().set(RedisEnum.VERIFY_TOKEN + newToken,userBO,redisToken, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            Log.info("token令牌无效");
            throw new TokenRuntimeException("Token解析异常");
        }
        return true;

    }


}
