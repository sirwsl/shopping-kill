package com.wsl.shoppingkill.serviceImpl;

import com.wsl.shoppingkill.component.snowflake.SnowFlake;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.param.AddOrderParam;
import com.wsl.shoppingkill.service.AddOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : WangShiLei
 * @date : 2021/1/2 12:12 上午
 **/

@Service
@Slf4j
public class AddOrderServiceImpl implements AddOrderService {


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private CuratorFramework curatorFramework;

    @Override
    public String commonOrder(AddOrderParam addOrderParam) {
        CorrelationData correlationData = new CorrelationData(String.valueOf(snowFlake.nextId()));
        String lockPath = "/lock/common/";
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(curatorFramework, lockPath + snowFlake.nextId());
        try {
            boolean flags = lock.acquire(10, TimeUnit.SECONDS);
            if (flags) {
                Object o = redisTemplate.opsForValue().get(RedisEnum.GOODS_SKU_NUM + addOrderParam.getSkuId());
                int num = 0;
                if (Objects.isNull(o)) {
                    num = skuMapper.selectById(addOrderParam.getSkuId()).getNum();
                } else {
                    num = Integer.parseInt(o.toString());
                    if (num < 1) {
                        num = skuMapper.selectById(addOrderParam.getSkuId()).getNum();
                    }
                }
                if (num > 1) {
                    rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_ORDER_COMMON, RabbitMqEnum.Key.KEY_ORDER_COMMON, addOrderParam, correlationData);
                    redisTemplate.opsForValue().set(RedisEnum.GOODS_SKU_NUM + addOrderParam.getSkuId(), String.valueOf(num - 1));
                    boolean isAck = correlationData.getFuture().get(10, TimeUnit.SECONDS).isAck();
                    if (isAck) {
                        return correlationData.getId();
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.info("下单失败{}", e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            try {
                lock.release();
            } catch (Exception es) {
                log.info("错误信息：{}", es.getMessage());
            }
        }
        return null;
    }

    @Override
    public String killOrder(AddOrderParam addOrderParam) {
        String lockPath = "/lock/kill/";
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(curatorFramework, lockPath + snowFlake.nextId());
        String key = RedisEnum.GOODS_KILL + addOrderParam.getSkuId();
        try {
            boolean flags = lock.acquire(60, TimeUnit.SECONDS);
            if (flags) {
                //库存减1
                log.info("========================线程:{}，获取到了锁", Thread.currentThread().getName());
                Object value = redisTemplate.opsForValue().get(key);
                if (Objects.nonNull(value) && Integer.parseInt(Objects.toString(value)) > 0) {
                    redisTemplate.boundValueOps(key).decrement(1);
                    CorrelationData correlationData = new CorrelationData(String.valueOf(snowFlake.nextId()));
                    rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_ORDER_KILL, RabbitMqEnum.Key.KEY_ORDER_KILL, addOrderParam, correlationData);
                    boolean isAck = correlationData.getFuture().get(1, TimeUnit.MINUTES).isAck();
                    if (isAck) {
                        return correlationData.getId();
                    } else {
                        throw new Exception();
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            try {
                boolean flags = lock.acquire(60, TimeUnit.SECONDS);
                if (flags) {
                    redisTemplate.boundValueOps(key).increment(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    lock.release();
                } catch (Exception es) {
                    log.info("错误信息：{}", es.getMessage());
                }
            }
            return null;
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                log.info("错误信息：{}", e.getMessage());
            }
        }
        return null;
    }
}
