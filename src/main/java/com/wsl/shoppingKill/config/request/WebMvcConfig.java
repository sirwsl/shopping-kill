package com.wsl.shoppingKill.config.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/23-18:58
 **/


@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer  {
    /*
     * 注入 token 拦截器
     */

    @Resource
    private TokenInterceptor interceptor;

    /**
     * 重写添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义拦截器，并拦截对应 url
        registry.addInterceptor(interceptor).addPathPatterns("/admin/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

}
