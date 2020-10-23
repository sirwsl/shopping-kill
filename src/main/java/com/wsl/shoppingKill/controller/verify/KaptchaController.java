package com.wsl.shoppingKill.controller.verify;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wsl.shoppingKill.entity.TestEntity;
import com.wsl.shoppingKill.enumerate.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

/**
 *   验证码的生成
 * @author wsl
 */
@RestController
@Slf4j
@RequestMapping("/verify")
public class KaptchaController {

    /**
     * 1、验证码工具
     */
    @Resource
    private DefaultKaptcha katha;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     * @param httpServletResponse:获取方式
     * @param user:用户
     * @throws Exception:验证码生成异常
     */
    @RequestMapping("/jpg")
    public void getKaptcha(HttpServletResponse httpServletResponse, TestEntity user)
            throws Exception {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到redis中
            String rightCode = katha.createText();
            log.info("rightCode:{}", rightCode);
            stringRedisTemplate.opsForValue().set(Constants.REDIS_PREFIX +user.getId(), rightCode,
                    Constants.KAPTCHA_EXPIRE_TIME, TimeUnit.SECONDS);

            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = katha.createImage(rightCode);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    /**
     * 校对验证码
     * @param user:使用者
     * @param tryCode:输入的验证码
     * @return bool:判断结果
     */
    public Boolean imgVerifyCode(TestEntity user, long goodsId, String tryCode) {
        String rightCode = stringRedisTemplate.opsForValue().get(Constants.REDIS_PREFIX + user.getId());
        log.info("rightCode={}, tryCode={}", rightCode, tryCode);
        return tryCode.equals(rightCode);
    }
}

