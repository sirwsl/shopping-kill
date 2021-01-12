package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.component.snowflake.SnowFlake;
import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.mapper.OrderMapper;
import com.wsl.shoppingkill.obj.bo.PayBO;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.param.PayParam;
import com.wsl.shoppingkill.service.PayService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 支付业务层
 * @author : WangShiLei
 * @date : 2021/1/1 2:10 下午
 **/

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Resource
    private SnowFlake snowFlake;

    @Override
    public boolean pay(PayParam payParam) {
        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq(Order.STATUS, BaseEnum.ORDER_TYPE_NOT_PAY)
                .eq(Order.ID, payParam.getOrderId()));
        if (Objects.isNull(order)){
            return false;
        }
        CorrelationData correlationData = new CorrelationData(String.valueOf(snowFlake.nextId()));
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();

        //TODO 支付逻辑有问题，以后重构
        /**
         * 调用支付接口
         * <P>
         *     支付接口返回，由于个人无法调用，所以当支付成功处理
         * </P>
         */
        PayBO payBO = new PayBO(id,payParam.getOrderId());

        rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_PAY,RabbitMqEnum.Key.KEY_PAY,payBO,correlationData);
        try {
            boolean isAck = correlationData.getFuture().get(1, TimeUnit.MINUTES).isAck();
            System.out.println(isAck);
            if(isAck){
                return true;
            }else{
                order.setPayType(payParam.getPayType())
                        .setStatus(BaseEnum.ORDER_TYPE_NOT_PAY)
                        .setAddressId(payParam.getAddressId())
                        .setRemark(payParam.getDetail())
                        .setPayTime(LocalDateTime.now())
                        .updateById();
            }
        }catch (Exception e){
            order.setPayType(payParam.getPayType())
                    .setStatus(BaseEnum.ORDER_TYPE_NOT_PAY)
                    .setAddressId(payParam.getAddressId())
                    .setRemark(payParam.getDetail())
                    .setPayTime(LocalDateTime.now())
                    .updateById();
        }
        return false;
    }
}
