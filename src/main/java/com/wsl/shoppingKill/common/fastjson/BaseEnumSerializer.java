package com.wsl.shoppingKill.common.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * @author : WangShiLei
 * @date : 2020-11-14 22:11
 **/
public class BaseEnumSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) {
        jsonSerializer.write(((BaseEnum)o).getDesc());
    }
}