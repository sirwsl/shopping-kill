package com.wsl.shoppingKill.service.subscription;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import com.wsl.shoppingKill.component.email.MailComponent;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.constant.SmsEnum;
import com.wsl.shoppingKill.domain.Subscriber;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.service.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 * @date 2020/11/13-16:27
 **/
@Component
@Slf4j
public class SubscriptionPush {


    @Resource
    private SubscriberService subscriberService;

    @Resource
    private SmsComponent smsComponent;

    @Resource
    private MailComponent mailComponent;

    /**
     * 监听短信发送
     *@param subscriptionHistory:
     * @author wangshilei
     * @date 2020/11/13 18:25
     **/
    @RabbitListener(queues = RabbitMqEnum.Queue.QUEUE_SMS)
    public void smsConsumerSubscription(SubscriptionHistory subscriptionHistory, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        List<String> phoneList = subscriberService.list(new QueryWrapper<Subscriber>()
                .eq(Subscriber.TYPE, BaseEnum.PHONE))
                .stream()
                .map(Subscriber::getNumber)
                .collect(Collectors.toList());
        String[] text = new String[3];
        text[1] = subscriptionHistory.getTitle();
        text[2] = subscriptionHistory.getDetail();
        phoneList.forEach(li-> {
            text[0] = li;
            smsComponent.send(SmsEnum.SUBSCRIPTION.getCode(),text,li);
        });

    }

    /**
     * 监听邮件发送
     *@param subscriptionHistory:
     * @author wangshilei
     * @date 2020/11/13 18:25
     **/
    @RabbitListener(queues = RabbitMqEnum.Queue.QUEUE_EMAIL)
    public void emailConsumerSubscription(SubscriptionHistory subscriptionHistory, Channel channel, Message message) throws IOException {
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            List<String> emailList = subscriberService.list(new QueryWrapper<Subscriber>()
                    .eq(Subscriber.TYPE, BaseEnum.EMAIL))
                    .stream()
                    .map(Subscriber::getNumber)
                    .collect(Collectors.toList());
            emailList.forEach(li-> {
                try {
                    mailComponent.sendSubscription(li,subscriptionHistory);
                } catch (Exception e) {
                    log.error("邮件推送失败用户mail：{}，内容为：{}",li,subscriptionHistory.getTitle());
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("短信订阅消息已重复处理失败,拒绝再次接收:{}", subscriptionHistory.getTitle());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            }
        }

    }
}

