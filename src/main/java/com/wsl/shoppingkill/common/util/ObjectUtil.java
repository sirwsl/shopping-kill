package com.wsl.shoppingkill.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangShilei
 * @date 2020/12/23-18:22
 **/
public class ObjectUtil {

    /**
     * <p>
     * List<String> list = castList(obj, String.class);
     * </p>
     * Object转list
     *
     * @param obj   :
     * @param clazz :
     * @return java.util.List<T>
     * @author wangShilei
     * @date 2020/12/23 18:23
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    public static <T> List<T> castIPageToList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof IPage<?>) {
            for (Object o : ((IPage<?>) obj).getRecords()) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    public static <T> IPage<T> castIPage(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof IPage<?>) {
            IPage<T> page = new Page<>();
            page.setTotal(((IPage<?>) obj).getTotal())
                    .setSize(((IPage<?>) obj).getSize())
                    .setCurrent(((IPage<?>) obj).getCurrent())
                    .setPages(((IPage<?>) obj).getPages());
            for (Object o : ((IPage<?>) obj).getRecords()) {
                result.add(clazz.cast(o));
            }
            page.setRecords(result);
            return page;
        }
        return null;
    }

}
