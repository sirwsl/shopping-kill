package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.SubscriptionHistoryMapper;
import com.wsl.shoppingKill.service.SubscriptionHistoryService;
/**
 * @author WangShilei
 */
@Service
public class SubscriptionHistoryServiceImpl extends ServiceImpl<SubscriptionHistoryMapper, SubscriptionHistory> implements SubscriptionHistoryService{


}
