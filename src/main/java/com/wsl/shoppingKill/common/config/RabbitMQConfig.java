package com.wsl.shoppingKill.common.config;

import com.wsl.shoppingKill.constant.RabbitMqEnum;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangShilei
 * @date 2020/11/13-13:34
 **/
@Configuration
public class RabbitMQConfig {


    @Bean
    public Queue queueSms(){
        return new Queue(RabbitMqEnum.Queue.QUEUE_SMS);
    }

}
