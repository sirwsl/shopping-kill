package com.wsl.shoppingKill.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author wsl
 * 2020-10-22 22:43
 * */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        // 字体间距
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "120");
        // 图片高
        properties.setProperty("kaptcha.image.height", "50");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 噪点生成对象
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,55");
        // 字体大小
        // properties.setProperty("kaptcha.textproducer.font.size", "30");
        // session key
        // properties.setProperty("kaptcha.session.key", "code");
        // 字体
        // properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");

        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

}
