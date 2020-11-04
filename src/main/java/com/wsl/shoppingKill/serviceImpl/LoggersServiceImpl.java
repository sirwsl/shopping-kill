package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Loggers;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.LoggersMapper;
import com.wsl.shoppingKill.service.LoggersService;
@Service
public class LoggersServiceImpl extends ServiceImpl<LoggersMapper, Loggers> implements LoggersService {

    @Resource
    private LoggersMapper loggersMapper;

}
