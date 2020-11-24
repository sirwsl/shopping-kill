package com.wsl.shoppingKill.service;

/**
 * 主页service
 * @author WangShilei
 * @date 2020/11/23-9:32
 **/
public interface HomeService {

    /**
     * 获取今日访问数量
     * @author wangShilei
     * @date 2020/11/23 9:34
     * @return java.lang.Integer
     */
    Integer getTodayNum();

    /**
     * 获取今日下单数量
     * @author wangShilei
     * @date 2020/11/23 9:35
     * @return java.lang.Integer
     */
    Integer getTodayOrder();

    /**
     * 获取今日出库
     * @author wangShilei
     * @date 2020/11/23 9:37
     * @return java.lang.Integer
     */
    Integer getTodayOut();

    /**
     * 获取今日收藏
     * @author wangShilei
     * @date 2020/11/23 9:37
     * @return java.lang.Integer
     */
    Integer getTodayLike();

    /**
     * 获取会员总数
     * @author wangShilei
     * @date 2020/11/23 9:38
     * @return java.lang.Integer
     */
    Integer getNumber();

    /**
     * 获取在线人数
     * @author wangShilei
     * @date 2020/11/23 9:38
     * @return java.lang.Integer
     */
    Integer getOnline();
}
