package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.mapper.SubscriptionHistoryMapper;
import com.wsl.shoppingKill.service.SubscriptionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
@Slf4j
public class SubscriptionHistoryServiceImpl extends ServiceImpl<SubscriptionHistoryMapper, SubscriptionHistory> implements SubscriptionHistoryService {

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Resource
    private SubscriptionHistoryMapper subscriptionHistoryMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @MyLog(detail = "添加发布订阅内容", value = "#subscriptionHistory.id", grade = LoggerEnum.INFO)
    public boolean sendNewsSubscription(SubscriptionHistory subscriptionHistory) {
        subscriptionHistory.setAdminId(abstractCurrentRequestComponent.getCurrentUser().getId());
        subscriptionHistoryMapper.insert(subscriptionHistory);
        if (subscriptionHistory.getType().equals(BaseEnum.PHONE)) {
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL, RabbitMqEnum.Key.KEY_USER_SMS, subscriptionHistory);
        } else if (subscriptionHistory.getType().equals(BaseEnum.EMAIL)) {
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL, RabbitMqEnum.Key.KEY_USER_EMAIL, subscriptionHistory);
        }

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.debug(ack + "    " + "已经应答  cause:{} " + cause + " correlationDate:{}" + correlationData);
            if (ack) {
                subscriptionHistory.setRealFlag(true).updateById();
            } else {
                log.warn("消息应答出错，未应答标题为{}，消息id为{}", subscriptionHistory.getTitle(), subscriptionHistory.getId());
            }
        });
        return true;
    }
}
