package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Loggers;
import com.wsl.shoppingkill.obj.vo.LoggersVO;

/**
 * @author wangShilei
 */
public interface LoggersService extends IService<Loggers> {

    /**
     * 获取日志操作记录
     * @param page :
     * @param num :
     * @param type :
     * @return IPage<LoggersVO>
     * @author wangshilei
     * @date 2020/11/9 13:46
     **/
    IPage<LoggersVO> getLogListLimit(Long page ,Long num, Integer type);
}
