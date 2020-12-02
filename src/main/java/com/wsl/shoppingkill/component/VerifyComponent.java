package com.wsl.shoppingkill.component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
     * @param response:获取方式
     * @throws Exception:验证码生成异常
     *
     */
    public void getKaptCha(HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到redis中
            String rightCode = katha.createText();
            log.info("rightCode:{}", rightCode);

            //存入session
            HttpSession session = request.getSession();
            session.setAttribute("code",rightCode);
            session.setMaxInactiveInterval(imgCodeTimeOut);

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
            String code = (String) request.getSession().getAttribute("code");
            log.info("rightCode={}, tryCode={}", code, tryCode);
            return tryCode.equalsIgnoreCase(code);

    }

}
