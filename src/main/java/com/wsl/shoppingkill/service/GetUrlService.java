package com.wsl.shoppingkill.service;

import com.wsl.shoppingkill.obj.bo.ExposerBO;

/**
 * @author : WangShiLei
 * @date : 2020/12/31 11:10 上午
 **/
public interface GetUrlService {

    /**
     * 获取秒杀商品连接
     * @author wangShilei
     * @date 2020/12/31 11:11 上午
     * @param id :
     * @return com.wsl.shoppingkill.obj.bo.ExposerBO
     */
    ExposerBO getUrl(Long id);


    /**
     * 获取md5加密后结果
     * @author wangShilei
     * @date 2020/12/31 11:28 上午
     * @param id :
     * @return java.lang.String
     */
    String getMd5(long id);
}
