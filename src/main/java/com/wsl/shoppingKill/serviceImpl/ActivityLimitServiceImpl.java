package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.ActivityLimit;
import org.springframework.stereotype.Service;
import com.wsl.shoppingKill.mapper.ActivityLimitMapper;
import com.wsl.shoppingKill.service.ActivityLimitService;

/**
 * @author WangShilei
 */

@Service
public class ActivityLimitServiceImpl extends ServiceImpl<ActivityLimitMapper, ActivityLimit> implements ActivityLimitService{

}
