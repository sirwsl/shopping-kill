package com.wsl.shoppingkill.serviceImpl.mq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.mapper.ActivityMapper;
import com.wsl.shoppingkill.mapper.OrderMapper;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 订单超时取消
 *
 * @author : WangShiLei
 * @date : 2021/1/1 7:56 下午
 **/

@Component
@Slf4j
public class OrderTimeOut {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RabbitListener(queues = RabbitMqEnum.Queue.QUEUE_ORDER_DEAD)
    @Transactional(rollbackFor = Exception.class)
    public void receive(Long orderId, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("订单超时取消，参数{}", orderId);
        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq(Order.ID, orderId).eq(Order.STATUS, BaseEnum.ORDER_TYPE_NOT_PAY));

        if (Objects.nonNull(order)) {
            //如果订单存在 删除订单 库存+1
            try {
                boolean flag1 = orderMapper.deleteById(orderId)>0;
                boolean flag2 = skuMapper.count(order.getSkuId());
                if (!(flag1 && flag2)){
                    throw new Exception();
                }
                Object o1 = redisTemplate.opsForValue().get(RedisEnum.GOODS_SKU_NUM + orderId);
                if (Objects.nonNull(o1)){
                    redisTemplate.boundValueOps(RedisEnum.GOODS_SKU_NUM + orderId).increment(1);
                }
                //如果redis中有需要更新redis库存
                String key = RedisEnum.GOODS_KILL + order.getSkuId();
                Object o = redisTemplate.opsForValue().get(key);
                if (Objects.nonNull(o)){
                    redisTemplate.boundValueOps(key).increment(1);
                    //只是做统计，允许小误差出错
                    activityMapper.count(order.getSkuId(),  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(order.getCreatTime()));
                }
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                log.warn("订单取消失败，尝试重新入队");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }

        }

    }
}
