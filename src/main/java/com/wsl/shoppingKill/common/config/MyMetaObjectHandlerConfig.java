package com.wsl.shoppingKill.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : WangShiLei
 * @date : 2020/11/4 11:53 下午
 **/
@Component
public class MyMetaObjectHandlerConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.setFieldValByName("creatTime",localDateTime,metaObject);
        this.setFieldValByName("updateTime",localDateTime,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.setFieldValByName("updateTime",localDateTime,metaObject);

    }
}