package com.wsl.shoppingKill.service;

/**
 * 主页service
 * @author WangShilei
 * @date 2020/11/23-9:32
 **/
public interface HomeService {

    /**
     * 获取今日访问数量
     *
     * @return java.lang.Integer
     * @author wangShilei
     * @date 2020/11/23 9:34
     */
    Integer getTodayNum();

    /**
     * 获取今日下单数量
     *
     * @return java.lang.Integer
     * @author wangShilei
     * @date 2020/11/23 9:35
     */
    Integer getTodayOrder();

    /**
     * 获取今日出库
     *
     * @return java.lang.Integer
     * @author wangShilei
     * @date 2020/11/23 9:37
     */
    Integer getTodayOut();

    /**
     * 获取会员总数
     *
     * @return java.lang.Integer
     * @author wangShilei
     * @date 2020/11/23 9:38
     */
    Integer getNumber();

    

}
