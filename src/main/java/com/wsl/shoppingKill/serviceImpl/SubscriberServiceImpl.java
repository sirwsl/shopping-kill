package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Subscriber;
import com.wsl.shoppingKill.mapper.SubscriberMapper;
import com.wsl.shoppingKill.service.SubscriberService;
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
    public IPage<Subscriber> getSubscriber(Integer size, Integer current,Integer type) {
        return subscriberMapper.selectPage(new Page<>(current, size),
                new QueryWrapper<Subscriber>().eq(Subscriber.TYPE,type));
    }


    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        return subscriberMapper.insert(subscriber) > 0;
    }

    @Override
    public boolean delSubscriber(String number) {
        return subscriberMapper.deleteById(number)>0;
    }
}
