package com.wsl.shoppingKill.serviceImpl.notice;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.constant.SmsEnum;
import com.wsl.shoppingKill.domain.Subscriber;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.service.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 * @date 2020/11/13-16:27
 **/
@Component
@Slf4j
public class ConsumerSubscriptionImpl {


    @Resource
    private SubscriberService subscriberService;

    @Resource
    private SmsComponent smsComponent;


    /**
     * TODO： 未测试
     * 监听短信发送
     *@param subscriptionHistory:
     * @author wangshilei
     * @date 2020/11/13 18:25
     **/
    @RabbitListener(queues = RabbitMqEnum.Queue.QUEUE_SMS)
    public void smsConsumerSubscription(SubscriptionHistory subscriptionHistory){
        List<String> phoneList = subscriberService.list(new QueryWrapper<Subscriber>()
                .eq(Subscriber.TYPE, BaseEnum.PHONE))
                .stream()
                .map(Subscriber::getNumber)
                .collect(Collectors.toList());
        String[] text = new String[2];
        text[0] = subscriptionHistory.getTitle();
        text[1] = subscriptionHistory.getDetail();
        phoneList.forEach(li-> smsComponent.send(SmsEnum.SUBSCRIPTION.getCode(),text,li));

        subscriptionHistory.setReal(true).updateById();
    }

    //TODO:缺少邮件监听

}
