package com.wsl.shoppingkill.service;

import com.wsl.shoppingkill.obj.param.PayParam;

/**
 * @author : WangShiLei
 * @date : 2021/1/1 2:10 下午
 **/
public interface PayService {

    /**
     * 支付接口
     * @author wangShilei
     * @date 2021/1/1 3:59 下午
     * @param payParam :
     * @return boolean
     */
    boolean pay(PayParam payParam);
}
