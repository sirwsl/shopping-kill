package com.wsl.shoppingKill.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wsl.shoppingKill.common.fastjson.BaseEnum;

/**
 * @author : WangShiLei
 * @date : 2020-11-04$ 21:44$
 **/

public enum  SexEnum implements IEnum<Integer>, BaseEnum {
    /**
     * 性别枚举
     */
    MAN(1,"男"),
    WOMAN(2,"女");

    @EnumValue
    private final int value;

    private final String desc;

    SexEnum(int value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }

}
