package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.CommonUtil;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.Cart;
import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.mapper.CartMapper;
import com.wsl.shoppingkill.mapper.OrderMapper;
import com.wsl.shoppingkill.obj.bo.OrderMqBO;
import com.wsl.shoppingkill.obj.bo.SmsObject;
import com.wsl.shoppingkill.obj.constant.*;
import com.wsl.shoppingkill.obj.exception.ExperienceException;
import com.wsl.shoppingkill.obj.param.OrderParam;
import com.wsl.shoppingkill.obj.vo.OrderDetailVO;
import com.wsl.shoppingkill.obj.vo.OrderVO;
import com.wsl.shoppingkill.obj.vo.UserOrderVO;
import com.wsl.shoppingkill.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author WangShilei
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Override
    public IPage<OrderVO> getAllOrder(OrderParam orderParam, Long current, Long size) {
        final IPage<OrderVO> allOrder = orderMapper.getAllOrder(new Page<>(current, size), orderParam);
        if (CollectionUtils.isNotEmpty(allOrder.getRecords())) {
            allOrder.getRecords().forEach(li -> li.setSendPhone(CommonUtil.replaceUserName(li.getSendPhone()))
                    .setSendName(CommonUtil.replaceUserName(li.getSendName())));
        }

        return allOrder;
    }

    @Override
    public boolean remind2Pay(Long orderId) throws ExperienceException {

        try {
            if (Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                    && abstractCurrentRequestComponent.getCurrentUser().getFlag() == 1000) {
                throw new ExperienceException("体验账号权限不足");
            }
        } catch (Exception e) {
            throw new ExperienceException("体验账号权限不足");
        }

        SmsObject smsObject = reminderInfoObjet(orderId).setCode(SmsEnum.REMIND_PAY.getCode());
        if (Objects.isNull(smsObject)) {
            return false;
        }
        rabbitTemplate.convertAndSend(
                RabbitMqEnum.Exchange.EXCHANGE_NOTICE,
                RabbitMqEnum.Key.KEY_NOTICE_SMS,
                smsObject);
        return true;
    }

    @Override
    @MyLog(value = "#orderId", detail = "修改物品价格", grade = LoggerEnum.SERIOUS)
    public boolean modifyPrice(Long orderId, BigDecimal bigDecimal) {
        Order order = new Order();
        order.setId(orderId).setPayPrice(bigDecimal);
        return orderMapper.updateById(order) > 0;
    }

    @Override
    @MyLog(value = "#orderId", detail = "物品出库", grade = LoggerEnum.WORN)
    public boolean outGoods(Long orderId) {
        SmsObject smsObject = reminderInfoObjet(orderId).setCode(SmsEnum.OUT_STOCK.getCode());
        if (Objects.isNull(smsObject)) {
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
    public boolean reminderEvaluation(Long orderId) throws ExperienceException {
        try {
            if (Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                    && abstractCurrentRequestComponent.getCurrentUser().getFlag() == 1000) {
                throw new ExperienceException("体验账号权限不足");
            }
        } catch (Exception e) {
            throw new ExperienceException("体验账号权限不足");
        }
        SmsObject smsObject = reminderInfoObjet(orderId).setCode(SmsEnum.REMIND_EVALUATION.getCode());
        if (Objects.isNull(smsObject)) {
            return false;
        }
        rabbitTemplate.convertAndSend(
                RabbitMqEnum.Exchange.EXCHANGE_NOTICE,
                RabbitMqEnum.Key.KEY_NOTICE_SMS,
                smsObject);
        return false;
    }

    @Override
    public Map<String, Integer> getOrderCount() {
        Map<String, Integer> result = new HashMap<>(8);
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        Integer noPay = 0;
        Integer noGet = 0;
        Integer noComm = 0;
        Integer cart = 0;
        noPay = orderMapper.selectCount(new QueryWrapper<Order>().eq(Order.USER_ID, id).eq(Order.STATUS, BaseEnum.ORDER_TYPE_NOT_PAY));
        noGet = orderMapper.selectCount(new QueryWrapper<Order>().eq(Order.USER_ID, id).eq(Order.STATUS, BaseEnum.ORDER_TYPE_OUT));
        noComm = orderMapper.selectCount(new QueryWrapper<Order>().eq(Order.USER_ID, id).eq(Order.STATUS, BaseEnum.ORDER_TYPE_GET));
        cart = cartMapper.selectCount(new QueryWrapper<Cart>().eq(Cart.USER_ID, id));

        result.put("noPay", noPay);
        result.put("noGet", noGet);
        result.put("noComm", noComm);
        result.put("cart", cart);
        return result;
    }

    @Override
    public IPage<UserOrderVO> getOrderInfo(Integer status, String name, Long current, Long size) {
        if (StringUtils.isNotEmpty(name)) {
            status = null;
        }
        IPage<UserOrderVO> voiPage = orderMapper.selectUserOrderInfo(new Page<>(current, size), status, name);
        voiPage.getRecords().forEach(li -> {
            li.setTotalPrice(li.getPrice().multiply(new BigDecimal(li.getNum())).add(li.getLogisticsPrice()));
            li.setImgUrl(li.getImgUrl() + "?x-oss-process=image/resize,m_fill,h_50,w_50");
        });

        return voiPage;

    }

    @Override
    public OrderDetailVO getOrderDetailVO(Long id) {
        OrderDetailVO detailVO = orderMapper.selectOrderDetail(id);
        if (Objects.isNull(detailVO)) {
            return new OrderDetailVO();
        }
        detailVO.setTotalPrice(detailVO.getPrice().multiply(new BigDecimal(detailVO.getNum())).add(detailVO.getLogisticsPrice()));
        detailVO.setImgUrl(detailVO.getImgUrl() + "?x-oss-process=image/resize,m_fill,h_100,w_100");
        return detailVO;
    }

    @Override
    public boolean ackGoods(Long orderId) {
        return orderMapper.updateById(new Order().setId(orderId).setStatus(BaseEnum.ORDER_TYPE_GET)) > 0;
    }

    /***内部使用紧张外调****/
    private SmsObject reminderInfoObjet(Long orderId) {

        OrderMqBO orderSendInfo = orderMapper.getOrderSendInfo(orderId);
        SmsObject smsObject = new SmsObject();

        ArrayList<String> objects = new ArrayList<>();
        objects.add(orderSendInfo.getUserName());
        objects.add(orderSendInfo.getGoodsName());
        smsObject.setPhone(orderSendInfo.getPhone()).setDescription(objects);

        return smsObject;
    }
}
