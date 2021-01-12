package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsl.shoppingkill.domain.Activity;
import com.wsl.shoppingkill.obj.bo.KillGoodsBO;
import com.wsl.shoppingkill.obj.param.ActivityParam;
import com.wsl.shoppingkill.obj.vo.ActivityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangShilei
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

   /**
    * 获取秒杀活动列表详情
    *
    * @param activityParam : status:1-未开始 2-进行中 3-已结束
    * @return IPage<com.wsl.shoppingkill.obj.vo.ActivityVO>
    * @author wangShilei
    * @date 2020/11/29 9:58 下午
    */
   List<ActivityVO> getActivityAll(@Param("activity") ActivityParam activityParam);

   /**
    * 根据商品id获取正在进行的活动
    *
    * @param ids  :
    * @param time :
    * @return java.util.List<com.wsl.shoppingkill.obj.bo.KillGoodsBO>
    * @author wangShilei
    * @date 2020/12/28 11:26 上午
    */
   List<KillGoodsBO> getKillGoods(@Param("ids") List<Long> ids, @Param("time") String time);

   /**
    * 库存数量+1
    *
    * @param skuId  :
    * @param format :
    * @return boolean
    * @author wangShilei
    * @date 2021/1/2 3:28 上午
    */
   boolean count(@Param("skuId") Long skuId, @Param("format") String format);


   /**
    * 库存数量+1
    *
    * @param skuId  :
    * @param format :
    * @return boolean
    * @author wangShilei
    * @date 2021/1/2 3:28 上午
    */
   boolean desert(@Param("skuId") Long skuId, @Param("format") String format);
}