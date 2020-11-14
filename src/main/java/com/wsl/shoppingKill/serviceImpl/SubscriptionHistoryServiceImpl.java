package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.mapper.SubscriptionHistoryMapper;
import com.wsl.shoppingKill.service.SubscriptionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
@Slf4j
public class SubscriptionHistoryServiceImpl extends ServiceImpl<SubscriptionHistoryMapper, SubscriptionHistory> implements SubscriptionHistoryService{

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Resource
    private SubscriptionHistoryMapper subscriptionHistoryMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean sendNewsSubscription(SubscriptionHistory subscriptionHistory) {
         subscriptionHistory.setAdminId(abstractCurrentRequestComponent.getCurrentUser().getId());

         try {
             if(subscriptionHistory.getType().equals(BaseEnum.PHONE)){
                 rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL,RabbitMqEnum.Key.KEY_USER_SMS,subscriptionHistory);
             }else if(subscriptionHistory.getType().equals(BaseEnum.EMAIL)){
                 rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL,RabbitMqEnum.Key.KEY_USER_EMAIL,subscriptionHistory);
             }else {
                 rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_FANOUT_NOTICE,"",subscriptionHistory);
             }
             subscriptionHistoryMapper.insert(subscriptionHistory);
             return true;
         }catch (Exception e){
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
         }

        return false;
    }
}
