package com.wsl.shoppingKill.common.config;

import com.wsl.shoppingKill.constant.RabbitMqEnum;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/13-13:34
 **/
@Configuration
public class RabbitMqConfig {

    @Resource
    private RabbitAdmin rabbitAdmin;

    /**
     * Queue
     **/
    @Bean
    public Queue queueSms(){
        return new Queue(RabbitMqEnum.Queue.QUEUE_SMS);
    }

    @Bean
    public Queue queueMail(){
        return new Queue(RabbitMqEnum.Queue.QUEUE_EMAIL);
    }

    /**
     * Exchange:
     * DirectExchange 一对一
     * TopicExchange 匹配
     * FanoutExchange 广播
     **/
    @Bean
    public TopicExchange exchangeSmsAndEmail(){
        return new TopicExchange(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL);
    }

    @Bean
    public FanoutExchange exchangeSmsAndEmailFanout(){
        return new FanoutExchange(RabbitMqEnum.Exchange.EXCHANGE_FANOUT_NOTICE);
    }


    //绑定规则
    /**
     * 绑定短信邮箱FanoutExchange队列
     **/

    @Bean
    Binding bindSms(){
        return BindingBuilder.bind(queueSms()).to(exchangeSmsAndEmailFanout());
    }

    @Bean
    Binding bindSmsEmail(){
        return BindingBuilder.bind(queueMail()).to(exchangeSmsAndEmailFanout());
    }
    /**
     * 分别绑定各自队列
     **/
    @Bean
    Binding bindEverySms(){
        return BindingBuilder.bind(queueSms()).to(exchangeSmsAndEmail()).with(RabbitMqEnum.Key.KEY_SMS);
    }

    @Bean
    Binding bindEveryEmail(){
        return BindingBuilder.bind(queueMail()).to(exchangeSmsAndEmail()).with(RabbitMqEnum.Key.KEY_EMAIL);
    }

    /**
     * 初始化admin对象
     **/
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 创建交换机和队列
     **/
    @Bean
    public void createExchangeQueue(){
        rabbitAdmin.declareExchange(exchangeSmsAndEmail());
        rabbitAdmin.declareExchange(exchangeSmsAndEmailFanout());

        rabbitAdmin.declareQueue(queueMail());
        rabbitAdmin.declareQueue(queueMail());
    }
}
