package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.ActivityLog;
import com.wsl.shoppingKill.mapper.ActivityLogMapper;
import com.wsl.shoppingKill.service.ActivityLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @author wsl
 */
@Service
public class ActivityLogServiceImpl extends ServiceImpl<ActivityLogMapper, ActivityLog> implements ActivityLogService{

    @Resource
    private ActivityLogMapper activityLogMapper;

}
