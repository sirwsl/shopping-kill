package com.wsl.shoppingkill.serviceImpl.mq;

import com.rabbitmq.client.Channel;
import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.mapper.OrderMapper;
import com.wsl.shoppingkill.obj.bo.PayBO;
import com.wsl.shoppingkill.obj.bo.SmsObject;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 支付mq限流处理
 * @author : WangShiLei
 * @date : 2021/1/1 2:18 下午
 **/
@Component
@Slf4j
public class PayHandle {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 短信通知创建管理员
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     * @param payBO:
     * @param channel:
     * @param message:
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_PAY,exclusive = "false",autoDelete = "false",durable = "true"),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_PAY),
            key = RabbitMqEnum.Key.KEY_PAY,
            ignoreDeclarationExceptions = "true"
    ))
    @RabbitHandler
    public void addAdminSms(PayBO payBO, Channel channel, Message message) throws IOException {
        try {

            Order order = orderMapper.selectById(payBO.getOrderId());
            order.setStatus(BaseEnum.ORDER_TYPE_PAY).updateById();
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

        } catch (IOException e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("支付失败，尝试重新入队操作");
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
            e.printStackTrace();
        }
    }

}
