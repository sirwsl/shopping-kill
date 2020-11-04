package com.wsl.shoppingKill.common.fastjson;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * @author : WangShiLei
 * @date : 2020-11-04
 **/
public class IEnumDeSerializer extends JsonDeserializer<IEnum> {

    @Override
    public IEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode value = jsonParser.getCodec().readTree(jsonParser);
        String currentName = jsonParser.currentName();
        Object currentValue = jsonParser.getCurrentValue();
        Class findPropertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        if (findPropertyType.isEnum()) {
            IEnum[] enumConstants = (IEnum[]) findPropertyType.getEnumConstants();
            for (IEnum e : enumConstants) {
                if (e.getValue().equals(value.asInt())) {
                    return e;
                }
            }
        }
        return null;
    }
}