package com.wsl.shoppingKill.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collections;
import java.util.List;

/**
 * 分页工具类
 * @author WangShilei
 * @date 2020/11/6-17:00
 **/
public class PageUtil {
    public static <T> List<T> getPageList(List<T> list, Integer current, Integer size){
        if (list.size() == 0){
            return list;
        }
        if (current == null || current== 0){
            current = 1;
        }
        if (size == null || size == 0){
            size = 10;
        }

        int fromIndex = (current - 1) * size;
        if (fromIndex >= list.size() || fromIndex < 0){
            return Collections.emptyList();
        }

        int toIndex = current * size;
        if (toIndex > list.size()){
            toIndex = list.size();
        }
        return list.subList(fromIndex,toIndex);
    }

    public static <T> Page<T> getPage(List<T> list, Integer current, Integer size)  {
        if (current == null || current== 0){
            current = 1;
        }
        if (size == null || size == 0){
            size = 10;
        }
        List<T> pageList = getPageList(list, current, size);
        Page<T> page = new Page<>(current,size);
        double pages = Math.ceil((double) list.size() / (double) size);
        page.setTotal(list.size());
        page.setRecords(pageList);
        page.setPages((long) pages);
        return page;
    }
}

