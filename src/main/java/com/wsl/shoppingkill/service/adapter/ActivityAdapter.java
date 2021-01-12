package com.wsl.shoppingkill.service.adapter;

import com.wsl.shoppingkill.obj.vo.KillGoodsVO;

import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/12/27 6:26 下午
 **/
public interface ActivityAdapter {

    /**
     * 获取真正进行中的活动
     * @author wangShilei
     * @date 2020/12/27 6:36 下午
     * @return List<KillGoodsVO>
     */
    List<KillGoodsVO> getActivityDoing();
}
