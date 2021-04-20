package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.domain.Loggers;
import com.wsl.shoppingkill.mapper.LoggersMapper;
import com.wsl.shoppingkill.mapper.UserMapper;
import com.wsl.shoppingkill.service.HomeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private LoggersMapper loggersMapper;

    @Override
    public Integer getTodayNum() {
        String count = stringRedisTemplate.opsForValue().get(RedisEnum.COUNT_USER_SUM);
        if (StringUtils.isEmpty(count)) {
            return 0;
        }
        return Integer.parseInt(count);
    }


    @Override
    public Integer getTodayOrder() {
        String count = stringRedisTemplate.opsForValue().get(RedisEnum.COUNT_ORDER_SUM);
        if (StringUtils.isEmpty(count)) {
            return 0;
        }
        return Integer.parseInt(count);
    }

    @Override
    public Integer getTodayOut() {
        String count = stringRedisTemplate.opsForValue().get(RedisEnum.COUNT_OUT_SUM);
        if (StringUtils.isEmpty(count)) {
            return 0;
        }
        return Integer.parseInt(count);
    }

    @Override
    public Integer getNumber() {
        return userMapper.selectCount(new QueryWrapper<>());
    }

    @Override
    public Map<String, IPage<Loggers>> getLoggersAll(Long current1, Long size1, Long current2, Long size2) {
        Map<String, IPage<Loggers>> loggers = new HashMap<>();
        IPage<Loggers> adminLog = loggersMapper.selectPage(new Page<>(current1, size1), new QueryWrapper<Loggers>().eq(Loggers.TYPE, BaseEnum.ADMIN).orderByDesc(Loggers.ID));
        IPage<Loggers> userLog = loggersMapper.selectPage(new Page<>(current2, size2), new QueryWrapper<Loggers>().eq(Loggers.TYPE, BaseEnum.USER).orderByDesc(Loggers.ID));
        loggers.put("admin", adminLog);
        loggers.put("user", userLog);
        return loggers;
    }


}
