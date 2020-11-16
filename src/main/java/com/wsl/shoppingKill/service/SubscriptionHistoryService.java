package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.SubscriptionHistory;

/**
 * @author wangShilei
 */
public interface SubscriptionHistoryService extends IService<SubscriptionHistory> {

    /**
     * 发送订阅消息
     * @param subscriptionHistory :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/13 16:02
     **/
    boolean sendNewsSubscription(SubscriptionHistory subscriptionHistory);


    /**
     * 获取全部订阅历史
     * @param current :
     * @param size :
     * @return IPage<SubscriptionHistory>
     * @author wangshilei
     * @date 2020/11/16 14:24
     **/
    IPage<SubscriptionHistory> getAllSubscriptionHistory(Integer current,Integer size);
}
