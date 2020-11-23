package com.wsl.shoppingKill.request;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.esotericsoftware.minlog.Log;
import com.wsl.shoppingKill.common.util.JwtTokenUtils;
import com.wsl.shoppingKill.constant.JwtEnum;
import com.wsl.shoppingKill.constant.RedisEnum;
import com.wsl.shoppingKill.obj.bo.UserBO;
import com.wsl.shoppingKill.obj.exception.TokenRuntimeException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author WangShilei
 * @date 2020/11/23-13:58
 **/
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String, UserBO> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头（如果有此请求头，表示token已经签发）
        String header = request.getHeader(JwtEnum.AUTH_HEADER_KEY);
        System.err.println("header token:" + header);
        if (StringUtils.isEmpty(header)) {
            throw new TokenRuntimeException("未登录");
        }
        //解析请求头（防止伪造token，token内容以"shoppingKill "作为开头）

        if (!header.startsWith(JwtEnum.TOKEN_PREFIX)) {
            throw new TokenRuntimeException("Token无效");
        }

        String token = header.substring(JwtEnum.TOKEN_PREFIX.length());
        try {
            if (JwtTokenUtils.getTokenTime(token) < 600) {
                UserBO userBO = redisTemplate.opsForValue().get(RedisEnum.VERIFY_TOKEN + token);
                if (Objects.isNull(userBO)) {
                    throw new TokenRuntimeException("Token解析异常");
                }
                String newToken = JwtTokenUtils.getToken(userBO);
                response.setHeader(JwtEnum.AUTH_HEADER_KEY, JwtEnum.TOKEN_PREFIX + newToken);
                redisTemplate.opsForValue().set(RedisEnum.VERIFY_TOKEN + newToken,userBO);
            }
        } catch (Exception e) {
            Log.info("token令牌无效");
            throw new TokenRuntimeException("Token解析异常,Token无效");
        }

        return true;

    }


}
