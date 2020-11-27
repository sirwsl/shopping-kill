package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.constant.RedisEnum;
import com.wsl.shoppingKill.constant.SmsEnum;
import com.wsl.shoppingKill.domain.Order;
import com.wsl.shoppingKill.mapper.OrderMapper;
import com.wsl.shoppingKill.obj.bo.OrderMqBO;
import com.wsl.shoppingKill.obj.bo.SmsObject;
import com.wsl.shoppingKill.obj.param.OrderParam;
import com.wsl.shoppingKill.obj.vo.OrderVO;
import com.wsl.shoppingKill.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author WangShilei
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public IPage<OrderVO> getAllOrder(OrderParam orderParam, Long current, Long size){
        return orderMapper.getAllOrder(new Page<>(current,size),orderParam);
    }

    @Override
    public boolean remind2Pay(Long orderId) {
        SmsObject smsObject = reminderInfoObjet(orderId).setCode(SmsEnum.REMIND_PAY.getCode());
        if(Objects.isNull(smsObject)){
            return false;
        }
        rabbitTemplate.convertAndSend(
                RabbitMqEnum.Exchange.EXCHANGE_NOTICE,
                RabbitMqEnum.Key.KEY_NOTICE_SMS,
                smsObject);
        return true;
    }

    @Override
    @MyLog(value = "#orderId",detail = "修改物品价格",grade = LoggerEnum.SERIOUS)
    public boolean modifyPrice(Long orderId, BigDecimal bigDecimal) {
        Order order = new Order();
        order.setId(orderId).setPayPrice(bigDecimal);
        return orderMapper.updateById(order) > 0;
    }

    @Override
    @MyLog(value = "#orderId",detail = "物品出库",grade = LoggerEnum.WORN)
    public boolean outGoods(Long orderId) {
        SmsObject smsObject = reminderInfoObjet(orderId).setCode(SmsEnum.OUT_STOCK.getCode());
        if(Objects.isNull(smsObject)){
            return false;
        }
//        rabbitTemplate.convertAndSend(
//                RabbitMqEnum.Exchange.EXCHANGE_NOTICE,
//                RabbitMqEnum.Key.KEY_NOTICE_SMS,
//                smsObject);
        stringRedisTemplate.boundValueOps(RedisEnum.COUNT_OUT_SUM).increment(1);
        return true;

    }

    @Override
    public boolean reminderEvaluation(Long orderId) {
        SmsObject smsObject = reminderInfoObjet(orderId).setCode(SmsEnum.REMIND_EVALUATION.getCode());
        if(Objects.isNull(smsObject)){
            return false;
        }
        rabbitTemplate.convertAndSend(
                RabbitMqEnum.Exchange.EXCHANGE_NOTICE,
                RabbitMqEnum.Key.KEY_NOTICE_SMS,
                smsObject);
        return false;
    }

    /***内部使用紧张外调****/
    private SmsObject reminderInfoObjet(Long orderId){

        OrderMqBO orderSendInfo = orderMapper.getOrderSendInfo(orderId);
        SmsObject smsObject = new SmsObject();

        ArrayList<String> objects = new ArrayList<>();
        objects.add(orderSendInfo.getUserName());
        objects.add(orderSendInfo.getGoodsName());
        smsObject.setPhone(orderSendInfo.getPhone()).setDescription(objects);

        return smsObject;
    }
}
