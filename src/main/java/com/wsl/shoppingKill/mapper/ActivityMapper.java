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

   /**
    * 获取秒杀活动列表详情
    * @author wangShilei
    * @date 2020/11/29 9:58 下午
    * @param page :
    * @param activityParam :
    * @return IPage<com.wsl.shoppingKill.obj.vo.ActivityVO>
    */
   IPage<ActivityVO> getActivityAll(Page<ActivityVO> page, @Param("activity") ActivityParam activityParam);
}