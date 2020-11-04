package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Activity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.ActivityMapper;
import com.wsl.shoppingKill.service.ActivityService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author WangShilei
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper,Activity> implements ActivityService{


}
