package com.wsl.shoppingkill.serviceImpl.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.wsl.shoppingkill.common.util.DateUtil;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.mapper.ActivityMapper;
import com.wsl.shoppingkill.mapper.OrderMapper;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.param.AddOrderParam;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 订单处理
 *
 * @author : WangShiLei
 * @date : 2021/1/2 12:18 上午
 **/

@Component
@Slf4j
public class OrderDealWith {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private ActivityMapper activityMapper;


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    /**
     * 普通订单处理
     *
     * @param addOrderParam:
     * @param channel:
     * @param message:
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_ORDER_COMMON,
                    exclusive = "false", autoDelete = "false", durable = "true",
                    arguments = {
                            @Argument(name = "x-max-length", value = "100000", type = "java.lang.Integer")
                    }),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_ORDER_COMMON),
            key = RabbitMqEnum.Key.KEY_ORDER_COMMON,
            ignoreDeclarationExceptions = "true"
    ))
    @Transactional(rollbackFor = Exception.class)
    public void commonOrder(AddOrderParam addOrderParam, Channel channel, Message message) throws IOException {
        try {
            Order order = new Order();
            Sku sku = skuMapper.selectById(addOrderParam.getSkuId());
            long oId = Long.parseLong(message.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString());
            BigDecimal expPrice = sku.getExpPrice();
            if (Objects.isNull(expPrice)) {
                expPrice = new BigDecimal(0);
            }
            order.setStatus(BaseEnum.ORDER_TYPE_NOT_PAY)
                    .setPayPrice(sku.getSellPrice().multiply(new BigDecimal(addOrderParam.getNum())).add(expPrice))
                    .setCreatTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now())
                    .setNum(addOrderParam.getNum())
                    .setSkuId(addOrderParam.getSkuId())
                    .setUserId(addOrderParam.getUserId())
                    .setOrderTime(LocalDateTime.now())
                    .setId(oId);
            orderMapper.insert(order);
            sku.setNum(sku.getNum() - 1).updateById();
            rabbitTemplate.convertAndSend(RabbitMqEnum.Queue.QUEUE_ORDER_DELAY, oId, msg -> {
                //过期时间60*5*1000
                msg.getMessageProperties().setExpiration("300000");
                return msg;
            });
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("普通订单处理失败，回滚订单，进入死信队列");
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.warn("普通订单处理失败，回滚订单，重新入队");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
            e.printStackTrace();
        }
    }


    /**
     * 秒杀订单处理
     *
     * @param addOrderParam:
     * @param channel:
     * @param message:
     * @author : WangShiLei
     * @date : 2020/11/15 10:55 下午
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMqEnum.Queue.QUEUE_ORDER_KILL,
                    exclusive = "false", autoDelete = "false", durable = "true",
                    arguments = {
                            @Argument(name = "x-max-length", value = "100000", type = "java.lang.Integer")
                    }),
            exchange = @Exchange(RabbitMqEnum.Exchange.EXCHANGE_ORDER_KILL),
            key = RabbitMqEnum.Key.KEY_ORDER_KILL,
            ignoreDeclarationExceptions = "true"
    ))
    public void killOrder(AddOrderParam addOrderParam, Channel channel, Message message) throws IOException {
        try {
            LocalDateTime now = LocalDateTime.now();
            Sku sku = skuMapper.selectById(addOrderParam.getSkuId());
            Order order = new Order();
            long oId = Long.parseLong(message.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString());
            BigDecimal expPrice = sku.getExpPrice();
            if (Objects.isNull(expPrice)) {
                expPrice = new BigDecimal(0);
            }
            order.setStatus(BaseEnum.ORDER_TYPE_NOT_PAY)
                    .setPayPrice(sku.getSellPrice().multiply(new BigDecimal(addOrderParam.getNum())).add(expPrice))
                    .setCreatTime(now)
                    .setUpdateTime(now)
                    .setNum(addOrderParam.getNum())
                    .setSkuId(addOrderParam.getSkuId())
                    .setUserId(addOrderParam.getUserId())
                    .setOrderTime(now)
                    .setId(oId);

            String key = RedisEnum.GOODS_KILL + addOrderParam.getSkuId();

            orderMapper.insert(order);
            sku.setNum(sku.getNum() - 1).updateById();
            activityMapper.desert(sku.getId(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now));

            rabbitTemplate.convertAndSend(RabbitMqEnum.Queue.QUEUE_ORDER_DELAY, oId, msg -> {
                //过期时间60*5*1000
                msg.getMessageProperties().setExpiration("300000");
                return msg;
            });
            //redisTemplate.opsForValue().set(RedisEnum.USER_KILL_NUM+id+sku.getId(),"1");

            Object o = redisTemplate.opsForValue().get(RedisEnum.GOODS_DOING + sku.getGoodsId());
            if (Objects.nonNull(o)) {
                ObjectMapper objectMapper = new ObjectMapper();
                KillGoodsVO killGoodsVO = objectMapper.convertValue(o, KillGoodsVO.class);
                killGoodsVO.setSum(killGoodsVO.getSum() - 1);
                redisTemplate.opsForValue().set(key + killGoodsVO.getId(), killGoodsVO,
                        DateUtil.distanceSecond(LocalDateTime.now(), killGoodsVO.getEndTime()), TimeUnit.SECONDS);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.warn("秒杀订单处理失败");
                /* 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列 */
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
            e.printStackTrace();
        }
    }


}
