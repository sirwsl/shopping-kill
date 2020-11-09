package com.wsl.shoppingKill.common.log;

import com.wsl.shoppingKill.constant.LoggerEnum;

import java.lang.annotation.*;
import java.util.PrimitiveIterator;

/**
 * 自定义注解类
 * Log日志存储
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    String detail() default "";
    LoggerEnum grade() default LoggerEnum.NONE;
}