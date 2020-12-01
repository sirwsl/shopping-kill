package com.wsl.shoppingkill.obj.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author WangShilei
 * @date 2020/11/9-10:41
 **/
public enum LoggerEnum {

    NONE(0),
    INFO(1),
    WORN(2),
    SERIOUS(3);

    @EnumValue
    @JsonValue
    private final int key;

    LoggerEnum(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
