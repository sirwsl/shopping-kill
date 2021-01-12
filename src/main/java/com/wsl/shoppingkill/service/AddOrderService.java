package com.wsl.shoppingkill.service;

import com.wsl.shoppingkill.obj.param.AddOrderParam;

/**
 * @author : WangShiLei
 * @date : 2021/1/2 12:12 上午
 **/
public interface AddOrderService {

    /**
     * 处理普通订单
     * @author wangShilei
     * @date 2021/1/2 12:14 上午
     * @param addOrderParam :
     * @return String
     */
    String commonOrder(AddOrderParam addOrderParam);

    /**
     * 处理秒杀订单
     * @author wangShilei
     * @date 2021/1/2 12:15 上午
     * @param addOrderParam :
     * @return String
     */
    String killOrder(AddOrderParam addOrderParam);

}
