package com.wsl.shoppingKill.service.subscription;

import com.rabbitmq.client.Channel;
import com.wsl.shoppingKill.component.email.MailComponent;
import com.wsl.shoppingKill.component.sms.SmsComponent;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.constant.SmsEnum;
import com.wsl.shoppingKill.domain.Admin;
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
     * @param admin:
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_ADD_ADMIN_SMS,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_ADMIN),
            key = RabbitMqEnum.Key.KEY_ADD_ADMIN,
            ignoreDeclarationExceptions = "true"
    ))
    public void addAdminSms(Admin admin, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            String[] send = new String[3];
            send[0] = admin.getName();
            send[1] = admin.getPhone();
            send[2] = admin.getPassword();
            smsComponent.send(SmsEnum.ADD_AUTHORIZATION.getCode(),send,admin.getPhone());
        } catch (IOException e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("管理员添加消息已重复处理失败,拒绝再次接收:{}",admin.getId());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }

    /**
     * 邮件通知创建管理员
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param admin:
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_ADD_ADMIN_MAIL,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_ADMIN),
            key = RabbitMqEnum.Key.KEY_ADD_ADMIN,
            ignoreDeclarationExceptions = "true"
    ))
    public void addAdminEmail(Admin admin, Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            mailComponent.sendAddAdmin(admin);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("管理员添加消息已重复处理失败,拒绝再次接收:{}", admin.getId());
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }
        /**
         * 短信通知删除管理员权限
         * @author : WangShiLei
         * @date : 2020/11/15 10:55 下午
         * @param admin:
         * @param channel:
         * @param message:
         **/
        @RabbitListener(bindings = @QueueBinding(
                value = @Queue(value = RabbitMqEnum.Queue.QUEUE_REMOVE_ADMIN_SMS,exclusive = "false",autoDelete = "false",durable = "true"),
                exchange = @Exchange(value = RabbitMqEnum.Exchange.EXCHANGE_ADMIN),
                key = RabbitMqEnum.Key.KEY_REMOVE_ADMIN,
                ignoreDeclarationExceptions = "true"
        ))
        public void delAdminSms(Admin admin, Channel channel, Message message) throws IOException {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                String[] send = new String[2];
                send[0] = admin.getName();
                send[1] = admin.getPhone();
                smsComponent.send(SmsEnum.REMOVE_AUTHORIZATION.getCode(),send,admin.getPhone());
            } catch (IOException e) {
                if (message.getMessageProperties().getRedelivered()) {
                    log.warn("管理员移除消息已重复处理失败,拒绝再次接收:{}",admin.getId());
                    /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                }else {
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
                }
                e.printStackTrace();
            }
        }

        /**
         * 邮件通知删除管理员权限
         * @author : WangShiLei
         * @date : 2020/11/15 10:55 下午
         * @param admin:
         * @param channel:
         * @param message:
         **/
        @RabbitListener(bindings = @QueueBinding(
                value = @Queue(value = RabbitMqEnum.Queue.QUEUE_REMOVE_ADMIN_MAIL,exclusive = "false",autoDelete = "false",durable = "true"),
                exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_ADMIN),
                key = RabbitMqEnum.Key.KEY_REMOVE_ADMIN,
                ignoreDeclarationExceptions = "true"
        ))
        public void delAdminEmail(Admin admin, Channel channel, Message message) throws IOException {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                mailComponent.sendDelAdmin(admin);
            } catch (Exception e) {
                if (message.getMessageProperties().getRedelivered()) {
                    log.warn("管理员删除通知已重复处理失败,拒绝再次接收:{}",admin.getId());
                    /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                }else {
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
                }
                e.printStackTrace();
            }
    }

}
