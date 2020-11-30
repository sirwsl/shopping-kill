package com.wsl.shoppingKill.common.component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author WangShilei
 * @date 2020/11/24-10:38
 **/
@Slf4j
@Component
public class VerifyComponent {

    @Value("${verify.imgCodeTimeOut}")
    private Integer imgCodeTimeOut;


    @Resource
    private DefaultKaptcha katha;

    /**
     * 生成验证码
     * @param httpServletResponse:获取方式
     * @throws Exception:验证码生成异常
     */
    public void getKaptCha(HttpServletResponse httpServletResponse, HttpServletRequest request)
            throws Exception {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到redis中
            String rightCode = katha.createText();
            log.info("rightCode:{}", rightCode);
            request.getSession().setAttribute("code",rightCode);
            request.getSession().setMaxInactiveInterval(imgCodeTimeOut);
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
     * @param request:
     * @param tryCode:输入的验证码
     * @return bool:判断结果
     */
    public Boolean imgVerifyCode(String tryCode,HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        log.info("rightCode={}, tryCode={}", code, tryCode);
        return tryCode.equalsIgnoreCase(code);
    }

}
