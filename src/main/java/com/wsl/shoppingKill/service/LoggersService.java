package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Loggers;
import com.wsl.shoppingKill.obj.vo.LoggersVO;

import java.util.List;

/**
 * @author wangShilei
 */
public interface LoggersService extends IService<Loggers> {

    /**
     * 获取日志操作记录
     * @param page : num : type
     * @return IPage<LoggersVO>
     * @author wangshilei
     * @date 2020/11/9 13:46
     **/
    IPage<LoggersVO> getLogListLimit(Long page ,Long num, Integer type);
}
