package com.wsl.shoppingKill.common.util;

import com.wsl.shoppingKill.constant.SexEnum;
import com.wsl.shoppingKill.domain.Admin;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangShilei
 * @date 2020/11/6-16:36
 **/
@Slf4j
public class ConvertObjUtil {

    /**
     * 对象转List
     *
     * @param obj:对象
     * @return list
     */
    public static ArrayList<Map<String, Object>> convertObjToList(Object obj) {
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            Map<String, Object> map = new HashMap<>();
            for (Field field : fields) {
                try {
                    Field f = obj.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    map.put(field.getName(), o);
                } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    log.error("异常", e);
                }
            }
            resultList.add(map);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            log.error("异常",e);
        }
        return resultList;
    }


    /**
     * 对象转map
     *
     * @param obj:对象
     * @return map
     */
    public static Map<String, Object> ConvertObjToMap(Object obj) {
        Map<String, Object> resultMap = new HashMap<>();
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                try {
                    Field f = obj.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    resultMap.put(field.getName(), o);
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    log.error("异常", e);
                }
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            log.error("异常",e);
        }
        return resultMap;
    }


    
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setSex(SexEnum.MAN).setAddress("地址test").setId(123L).setName("张三");
        System.out.println("list:" + convertObjToList(admin));

        System.out.println("map:"+ConvertObjToMap(admin));

    }
}
