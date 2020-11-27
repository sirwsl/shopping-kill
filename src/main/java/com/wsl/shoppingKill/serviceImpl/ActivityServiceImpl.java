package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Activity;
import com.wsl.shoppingKill.mapper.ActivityMapper;
import com.wsl.shoppingKill.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * @author WangShilei
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService{


}
