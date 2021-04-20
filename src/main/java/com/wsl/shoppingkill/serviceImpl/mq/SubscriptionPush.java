package com.wsl.shoppingkill.serviceImpl.mq;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import com.wsl.shoppingkill.component.email.MailComponent;
import com.wsl.shoppingkill.component.sms.SmsComponent;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.constant.SmsEnum;
import com.wsl.shoppingkill.domain.Subscriber;
import com.wsl.shoppingkill.domain.SubscriptionHistory;
import com.wsl.shoppingkill.obj.bo.MailObject;
import com.wsl.shoppingkill.service.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     *
     * @param subscriptionHistory:
     * @author wangshilei
     * @date 2020/11/13 18:25
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_SUBSCRIPTION_SMS, exclusive = "false", autoDelete = "false", durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_NOTICE),
            key = RabbitMqEnum.Key.KEY_SUBSCRIPTION_SMS,
            ignoreDeclarationExceptions = "true"
    ))
    public void smsConsumerSubscription(SubscriptionHistory subscriptionHistory, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        List<String> phoneList = subscriberService.list(new QueryWrapper<Subscriber>()
                .eq(Subscriber.TYPE, BaseEnum.PHONE))
                .stream()
                .map(Subscriber::getNumber)
                .collect(Collectors.toList());
        String[] text = new String[3];
        text[1] = subscriptionHistory.getTitle();
        text[2] = subscriptionHistory.getDetail();
        phoneList.forEach(li -> {
            text[0] = li;
            smsComponent.send(SmsEnum.SUBSCRIPTION.getCode(), Arrays.asList(text), li);
        });
        subscriptionHistory.setRealFlag(true).updateById();
    }

    /**
     * 监听邮件发送
     *
     * @param subscriptionHistory:
     * @author wangshilei
     * @date 2020/11/13 18:25
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_SUBSCRIPTION_MAIL, exclusive = "false", autoDelete = "false", durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_NOTICE),
            key = RabbitMqEnum.Key.KEY_SUBSCRIPTION_MAIL,
            ignoreDeclarationExceptions = "true"
    ))
    public void emailConsumerSubscription(SubscriptionHistory subscriptionHistory, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            List<String> emailList = subscriberService.list(new QueryWrapper<Subscriber>()
                    .eq(Subscriber.TYPE, BaseEnum.EMAIL))
                    .stream()
                    .map(Subscriber::getNumber)
                    .collect(Collectors.toList());
            MailObject mailObject = new MailObject();
            Map<String, String> map = new HashMap<>(8);
            map.put("title", subscriptionHistory.getTitle());
            map.put("detail", subscriptionHistory.getDetail());
            mailObject.setContent(map).setTemplate("Subscription.ftl").setSubject(subscriptionHistory.getTitle());
            emailList.forEach(li -> {
                mailObject.setNumber(li);
                try {
                    mailComponent.sendMail2Html(mailObject);
                } catch (Exception e) {
                    log.error("邮件推送失败用户mail：{}，内容为：{}", li, subscriptionHistory.getTitle());
                    e.printStackTrace();
                }
            });
            subscriptionHistory.setRealFlag(true).updateById();
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("短信订阅消息已重复处理失败,拒绝再次接收:{}", subscriptionHistory.getTitle());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        }

    }
}

