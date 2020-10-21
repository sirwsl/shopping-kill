package com.wsl.shoppingKill;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动类
 * @author wangshilei
 * @date 2020/10/19 13:55
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMethodCache(basePackages = { "com.wsl.shoppingKill" })
@EnableCreateCacheAnnotation
@MapperScan(basePackages = {"com.wsl.shoppingKill.mapper"})
@EntityScan(basePackages = {"com.wsl.shoppingKill.entity"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
