package com.wsl.shoppingKill.serviceImpl.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Loggers;
import com.wsl.shoppingKill.mapper.LoggersMapper;
import com.wsl.shoppingKill.obj.vo.LoggersVO;
import com.wsl.shoppingKill.service.admin.LoggersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class LoggersServiceImpl extends ServiceImpl<LoggersMapper, Loggers> implements LoggersService {

    @Resource
    private LoggersMapper loggersMapper;


    @Override
    public IPage<LoggersVO> getLogListLimit(Long page ,Long num, Integer type) {
        Page<LoggersVO> pages = new Page<>(page,num);
        IPage<LoggersVO> allLogByType = loggersMapper.getAllLogByType(pages, type);
        if (allLogByType.getSize()>0) {
            return allLogByType;
        }

        return null;
    }
}
