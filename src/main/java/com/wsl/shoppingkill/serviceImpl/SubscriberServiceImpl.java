package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.util.CommonUtil;
import com.wsl.shoppingkill.domain.Subscriber;
import com.wsl.shoppingkill.mapper.SubscriberMapper;
import com.wsl.shoppingkill.service.SubscriberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class SubscriberServiceImpl extends ServiceImpl<SubscriberMapper, Subscriber> implements SubscriberService {

    @Resource
    private SubscriberMapper subscriberMapper;

    @Override
    public IPage<Subscriber> getSubscriber(Integer size, Integer current, Integer type) {
        final IPage<Subscriber> subscriberIPage = subscriberMapper.selectPage(new Page<>(current, size),
                new QueryWrapper<Subscriber>().eq(Subscriber.TYPE, type));
        if (CollectionUtils.isNotEmpty(subscriberIPage.getRecords())) {
            subscriberIPage.getRecords().forEach(li -> li.setNumber(CommonUtil.replaceUserName(li.getNumber())));
        }

        return subscriberIPage;
    }


    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        return subscriberMapper.insert(subscriber) > 0;
    }

    @Override
    public boolean delSubscriber(Long number) {
        return subscriberMapper.deleteById(number) > 0;
    }
}
