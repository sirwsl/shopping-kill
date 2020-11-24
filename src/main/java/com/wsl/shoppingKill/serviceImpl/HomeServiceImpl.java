package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wsl.shoppingKill.constant.RedisEnum;
import com.wsl.shoppingKill.mapper.UserMapper;
import com.wsl.shoppingKill.service.HomeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/23-9:33
 **/
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer getTodayNum() {
        String count = stringRedisTemplate.opsForValue().get(RedisEnum.COUNT_USER_SUM);
        if (StringUtils.isEmpty(count)){
            return 0;
        }
        return Integer.parseInt(count);
    }


    @Override
    public Integer getTodayOrder() {
        String count = stringRedisTemplate.opsForValue().get(RedisEnum.COUNT_ORDER_SUM);
        if (StringUtils.isEmpty(count)){
            return 0;
        }
        return Integer.parseInt(count);
    }

    @Override
    public Integer getTodayOut() {
        String count = stringRedisTemplate.opsForValue().get(RedisEnum.COUNT_OUT_SUM);
        if (StringUtils.isEmpty(count)){
            return 0;
        }
        return Integer.parseInt(count);
    }

    @Override
    public Integer getNumber() {
        return userMapper.selectCount(new QueryWrapper<>());
    }


}
