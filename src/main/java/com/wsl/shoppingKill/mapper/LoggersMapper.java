package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.Loggers;
import com.wsl.shoppingKill.obj.vo.LoggersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
@Mapper
public interface LoggersMapper extends BaseMapper<Loggers> {
    /**
     * 获取全部日志类型
     * @param type:类型
     * @param page :
     * @return null
     * @author wangshilei
     * @date 2020/11/9 13:50
     **/
    IPage<LoggersVO> getAllLogByType(Page<LoggersVO> page,@Param("type") Integer type);
}