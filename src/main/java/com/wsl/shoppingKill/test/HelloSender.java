package com.wsl.shoppingKill.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单队列
 *
 * 更多例子参考
 * https://blog.csdn.net/hellozpc/article/details/81436980#8SpringbootRabbitMQ_1267
 * https://blog.csdn.net/aa1215018028/article/details/81325082
 *
 * @author wsl
 */
@Component
public class HelloSender {
    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + date;
        System.out.println("Sender : " + context);
        // 简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("other_queue", context);
    }
}