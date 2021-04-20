package com.wsl.shoppingkill.config;

import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列初始化
 *
 * @author : WangShiLei
 * @date : 2021/1/1 7:22 下午
 **/

@Configuration
@Slf4j
public class RabbitMqInitConfig {

    /**
     * 设置延迟队列并绑定死信
     *
     * @return Queue:
     */
    @Bean(RabbitMqEnum.Queue.QUEUE_ORDER_DELAY)
    public Queue delayQueue() {
        // 设置死信交换机与路由key
        //队列名称
        return QueueBuilder.durable(RabbitMqEnum.Queue.QUEUE_ORDER_DELAY)
                //指定死信路由交换机
                .withArgument("x-dead-letter-exchange", RabbitMqEnum.Exchange.EXCHANGE_ORDER_DEAD)
                .withArgument("x-dead-letter-routing-key", RabbitMqEnum.Key.KEY_ORDER_DEAD).build();
    }

    /**
     * 定义死信队列
     *
     * @return Queue:
     */
    @Bean(RabbitMqEnum.Queue.QUEUE_ORDER_DEAD)
    public Queue deadQueue() {
        return QueueBuilder.durable(RabbitMqEnum.Queue.QUEUE_ORDER_DEAD).build();
    }

    /**
     * 定义死信交换机
     *
     * @return Queue:
     */
    @Bean(RabbitMqEnum.Exchange.EXCHANGE_ORDER_DEAD)
    public Exchange deadExchange() {
        return ExchangeBuilder.directExchange(RabbitMqEnum.Exchange.EXCHANGE_ORDER_DEAD).build();
    }

    /**
     * 定义死信交换机
     *
     * @return Queue:
     */
    @Bean(RabbitMqEnum.Exchange.EXCHANGE_ORDER_DELAY)
    public Exchange delayExchange() {
        return ExchangeBuilder.directExchange(RabbitMqEnum.Exchange.EXCHANGE_ORDER_DELAY).build();
    }

    /**
     * 绑定
     */
    @Bean("deadBinding")
    public Binding deadBinding(@Qualifier(RabbitMqEnum.Exchange.EXCHANGE_ORDER_DEAD) Exchange exchange,
                               @Qualifier(RabbitMqEnum.Queue.QUEUE_ORDER_DEAD) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqEnum.Key.KEY_ORDER_DEAD).noargs();
    }

    /**
     * 绑定
     */
    @Bean("delayBinding")
    public Binding delayBinding(@Qualifier(RabbitMqEnum.Exchange.EXCHANGE_ORDER_DELAY) Exchange exchange,
                                @Qualifier(RabbitMqEnum.Queue.QUEUE_ORDER_DELAY) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqEnum.Key.KEY_ORDER_DELAY).noargs();
    }

}
