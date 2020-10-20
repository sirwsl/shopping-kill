package cn.cuit.rabbit.one2one.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wsl
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue() {
        return new Queue("other_queue");
    }
}