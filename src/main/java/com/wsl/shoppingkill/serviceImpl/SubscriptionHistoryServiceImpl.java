package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.SubscriptionHistory;
import com.wsl.shoppingkill.mapper.SubscriptionHistoryMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.exception.ExperienceException;
import com.wsl.shoppingkill.obj.param.PageTimeParam;
import com.wsl.shoppingkill.service.SubscriptionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

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
    public boolean sendNewsSubscription(SubscriptionHistory subscriptionHistory) throws ExperienceException {
        try {
            if (Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                    && abstractCurrentRequestComponent.getCurrentUser().getFlag() == 1000) {
                throw new ExperienceException("体验账号权限不足");
            }
        } catch (Exception e) {
            throw new ExperienceException("体验账号权限不足");
        }
        subscriptionHistory.setAdminId(abstractCurrentRequestComponent.getCurrentUser().getId());
        subscriptionHistoryMapper.insert(subscriptionHistory);
        if (subscriptionHistory.getType().equals(BaseEnum.PHONE)) {
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE, RabbitMqEnum.Key.KEY_SUBSCRIPTION_SMS, subscriptionHistory);
        } else if (subscriptionHistory.getType().equals(BaseEnum.EMAIL)) {
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE, RabbitMqEnum.Key.KEY_SUBSCRIPTION_MAIL, subscriptionHistory);
        }
        return true;
    }

    @Override
    public IPage<SubscriptionHistory> getAllSubscriptionHistory(Integer current, Integer size) {
        Page<SubscriptionHistory> page = new Page<>(current, size);
        return subscriptionHistoryMapper.selectPage(page,
                new QueryWrapper<SubscriptionHistory>().orderByDesc(SubscriptionHistory.ID));
    }

    @Override
    public IPage<SubscriptionHistory> getSubscriptionHistoryByTime(PageTimeParam pageTimeParam) {
        Page<SubscriptionHistory> page = new Page<>(pageTimeParam.getCurrent(), pageTimeParam.getSize());
        return subscriptionHistoryMapper.selectPage(page, new QueryWrapper<SubscriptionHistory>()
                .ge(SubscriptionHistory.CREAT_TIME, pageTimeParam.getBeginTime())
                .le(SubscriptionHistory.CREAT_TIME, pageTimeParam.getEndTime()));
    }

}
