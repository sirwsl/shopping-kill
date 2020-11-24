package com.wsl.shoppingKill.serviceImpl.subscription;

import com.rabbitmq.client.Channel;
import com.wsl.shoppingKill.component.email.MailComponent;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.constant.SmsEnum;
import com.wsl.shoppingKill.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/** 用户账户信息推送
 * @author WangShilei
 * @date 2020/11/17-11:06
 **/

@Service
@Slf4j
public class UserInfoPush {
    @Resource
    private SmsComponent smsComponent;

    @Resource
    private MailComponent mailComponent;

    /**
     * 短信通知删除用户
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_REMOVE_USER_SMS,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_USER),
            key = RabbitMqEnum.Key.KEY_REMOVE_USER_SMS,
            ignoreDeclarationExceptions = "true"
    ))
    public void delUserSms(User user, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            String[] send = new String[2];
            send[0] = user.getPhone();
            send[1] = user.getName();
            smsComponent.send(SmsEnum.REMOVE_AUTHORIZATION.getCode(),send,user.getPhone());
        } catch (IOException e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("用户信息删除消息重复处理失败,拒绝再次接收:{}",user.getId());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }

    /**
     * 邮件通知删除用户
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_REMOVE_USER_MAIL,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_USER),
            key = RabbitMqEnum.Key.KEY_REMOVE_USER_EMAIL,
            ignoreDeclarationExceptions = "true"
    ))
    public void delUserMail(User user, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            mailComponent.sendDelUser(user);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("用户信息删除消息已重复处理失败,拒绝再次接收:{}",user.getId());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }

    /**
     * 短信通知修改用户
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_UPDATE_USER_SMS,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_USER),
            key = RabbitMqEnum.Key.KEY_UPDATE_USER_SMS,
            ignoreDeclarationExceptions = "true"
    ))
    public void updateUserSms(User user, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            String[] send = new String[2];
            send[0] = user.getName();
            send[1] = user.getPassword();
            smsComponent.send(SmsEnum.UPDATE_INFO.getCode(),send,user.getPhone());
        } catch (IOException e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("修改用户信息消息已重复处理失败,拒绝再次接收:{}",user.getId());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }

    /**
     * 短信通知修改用户
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_UPDATE_USER_MAIL,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_USER),
            key = RabbitMqEnum.Key.KEY_UPDATE_USER_MAIL,
            ignoreDeclarationExceptions = "true"
    ))
    public void updateUserMail(User user, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            mailComponent.sendUpdateUser(user);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("修改用户信息消息已重复处理失败,拒绝再次接收:{}",user.getId());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }
}
