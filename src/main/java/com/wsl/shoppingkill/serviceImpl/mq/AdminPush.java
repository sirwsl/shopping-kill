package com.wsl.shoppingkill.serviceImpl.mq;

import com.rabbitmq.client.Channel;
import com.wsl.shoppingkill.component.email.MailComponent;
import com.wsl.shoppingkill.component.sms.SmsComponent;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.bo.MailObject;
import com.wsl.shoppingkill.obj.bo.SmsObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/** 管理员消息推送
 * @author : WangShiLei
 * @date : 2020/11/15 10:38 下午
 **/
@Service
@Slf4j
public class AdminPush {

    @Resource
    private SmsComponent smsComponent;

    @Resource
    private MailComponent mailComponent;

    /**
     * 短信通知创建管理员
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param smsObject:
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_NOTICE_SMS,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_NOTICE),
            key = RabbitMqEnum.Key.KEY_NOTICE_SMS,
            ignoreDeclarationExceptions = "true"
    ))
    public void addAdminSms(SmsObject smsObject, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            smsComponent.send(smsObject.getCode(),smsObject.getDescription(),smsObject.getPhone());
        } catch (IOException e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("管理员消息发送已重复处理失败,拒绝再次接收:{}",smsObject.getPhone());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }

    /**
     * 邮件通知
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param mailObject:
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_NOTICE_MAIL,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_NOTICE),
            key = RabbitMqEnum.Key.KEY_NOTICE_MAIL,
            ignoreDeclarationExceptions = "true"
    ))
    public void addAdminEmail(MailObject mailObject, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            mailComponent.sendMail2Html(mailObject);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("邮件消息已重复处理失败,拒绝再次接收:{}", mailObject.getNumber());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }

}
