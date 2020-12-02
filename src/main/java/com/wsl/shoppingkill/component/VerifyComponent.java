package com.wsl.shoppingkill.component;

import com.esotericsoftware.minlog.Log;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wsl.shoppingkill.component.snowflake.SnowFlake;
import com.wsl.shoppingkill.obj.constant.JwtEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.exception.TokenRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * @author WangShilei
 * @date 2020/11/24-10:38
 **/
@Slf4j
@Component
public class VerifyComponent {

    @Value("${verify.imgCodeTimeOut}")
    private Integer imgCodeTimeOut;

    @Value("${req.doMainUrl}")
    private String doMainUrl;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DefaultKaptcha katha;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 生成验证码
     * @param response:获取方式
     * @throws Exception:验证码生成异常
     *
     */
    public void getKaptCha(HttpServletResponse response)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到redis中
            String rightCode = katha.createText();
            log.info("rightCode:{}", rightCode);

            //存入redis
            String token = String.valueOf(snowFlake.nextId());
            response.setHeader(JwtEnum.AUTH_HEADER_KEY, JwtEnum.TOKEN_PREFIX+token);
            Cookie cookie = new Cookie(JwtEnum.TOKEN, URLEncoder.encode(JwtEnum.TOKEN_PREFIX + token, "UTF-8"));
            cookie.setMaxAge(imgCodeTimeOut);
            cookie.setPath("/");
            cookie.setDomain(doMainUrl);
            response.addCookie(cookie);
            stringRedisTemplate.opsForValue().set(RedisEnum.VERIFY_CODE+token,rightCode,imgCodeTimeOut, TimeUnit.SECONDS);

            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = katha.createImage(rightCode);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 校对验证码
     * @param request:
     * @param tryCode:输入的验证码
     * @return bool:判断结果
     */
    public Boolean imgVerifyCode(String tryCode,HttpServletRequest request) {
        //获取请求头（如果有此请求头，表示token已经签发）

        String header = request.getHeader(JwtEnum.AUTH_HEADER_KEY);

        if (StringUtils.isEmpty(header)) {
            for (Cookie cookie : request.getCookies()) {
                if (JwtEnum.TOKEN.equals(cookie.getName())){
                    header = cookie.getValue();
                }
            }
        }
        //解析请求头（防止伪造token，token内容以"shoppingkill "作为开头）
        if (!header.startsWith(JwtEnum.TOKEN_PREFIX)) {
            throw new TokenRuntimeException("验证出现问题，请稍后再试");
        }
        String token = header.substring(JwtEnum.TOKEN_PREFIX.length());
        try {
            String code = stringRedisTemplate.opsForValue().get(RedisEnum.VERIFY_CODE+token);
            log.info("rightCode={}, tryCode={}", code, tryCode);
            return tryCode.equalsIgnoreCase(code);
        } catch (Exception e) {
            Log.info("验证码失效");
            throw new TokenRuntimeException("验证码失效");
        }

    }

}
