package com.wsl.shoppingkill.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : WangShiLei
 * @date : 2020/11/25 10:16 下午
 **/
public class MapUtil {

    /**
     * Map集合对象转化成 JavaBean集合对象
     *
     * @param javaBean JavaBean实例对象
     * @param mapList Map数据集对象
     * @return List<T>
     */
    @SuppressWarnings({ "rawtypes" })
    public static <T> List<T> map2Java(T javaBean, List<Map> mapList) {
        if(mapList == null || mapList.isEmpty()){
            return null;
        }
        List<T> objectList = new ArrayList<>();

        T object;
        for(Map map : mapList){
            if(map != null){
                object = map2Java(javaBean, map);
                objectList.add(object);
            }
        }

        return objectList;

    }

    /**
     * Map对象转化成 JavaBean对象
     *
     * @param javaBean JavaBean实例对象
     * @param map Map对象
     * @return <T>
     */
    @SuppressWarnings({ "rawtypes","unchecked", "hiding" })
    public static <T> T map2Java(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                // javaBean属性名
                String propertyName;
                // javaBean属性值
                Object propertyValue;
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, propertyValue);
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * JavaBean对象转化成Map对象
     *
     * @param javaBean:
     * @return Map
     * @author jqlin
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map java2Map(Object javaBean) {
        Map map = new HashMap<>(8);

        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                // javaBean属性名
                String propertyName ;
                // javaBean属性值
                Object propertyValue ;
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (!"class".equals(propertyName)) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean);
                        map.put(propertyName, propertyValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }




}