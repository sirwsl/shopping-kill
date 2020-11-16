package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Subscriber;

/**
 * @author wangShilei
 */
public interface SubscriberService extends IService<Subscriber> {

    /**
     * 获取订阅者 短信
     * @param current:
     * @param size :
     * @param type:
     * @return null
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    IPage<Subscriber> getSubscriber(Integer size,Integer current,Integer type);

    /**
     * 添加订阅者
     * @param subscriber :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    boolean addSubscriber(Subscriber subscriber);

    /**
     * 删除订阅者
     * @param number :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/16 15:38
     **/
    boolean delSubscriber (String number);

}
