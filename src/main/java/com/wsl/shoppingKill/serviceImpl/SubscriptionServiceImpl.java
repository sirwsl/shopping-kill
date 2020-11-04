package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Subscription;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.SubscriptionMapper;
import com.wsl.shoppingKill.service.SubscriptionService;
/**
 * @author WangShilei
 */
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription> implements SubscriptionService{



}
