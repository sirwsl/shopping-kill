package com.wsl.shoppingkill;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * @author wangshilei
 * @date 2020/10/19 13:55
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMethodCache(basePackages = { "com.wsl.shoppingkill" })
@EnableCreateCacheAnnotation
@EntityScan(basePackages = {"com.wsl.shoppingkill.entity"})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
