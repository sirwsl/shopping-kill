package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.Activity;
import com.wsl.shoppingKill.obj.param.ActivityParam;
import com.wsl.shoppingKill.obj.vo.ActivityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

   IPage<ActivityVO> getActivityAll(Page<ActivityVO> page, @Param("activity") ActivityParam activityParam);
}