package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Activity;
import com.wsl.shoppingkill.obj.param.ActivityParam;
import com.wsl.shoppingkill.obj.param.ActivityUpdateParam;
import com.wsl.shoppingkill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingkill.obj.vo.ActivityVO;

import java.util.List;

/**
 * @author wangShilei
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 获取活动列表
     * @author wangShilei
     * @date 2020/11/30 9:10
     * @param activityParam :
     * @return List<ActivityVO>
     */
    List<ActivityVO> getActivityAll(ActivityParam activityParam);


    /**
     * 获取待添加活动列表
     * @author wangShilei
     * @date 2020/11/30 9:20
     * @param current :
     * @param size :
     * @param id :
     * @param name :
     * @return IPage<com.wsl.shoppingkill.obj.vo.ActivityByGoodsVO>
     */
    IPage<ActivityByGoodsVO> getActivityByGoods(Long current, Long size, Long id, String name);

    /**
     * 更新一个活动
     * @author wangShilei
     * @date 2020/11/30 9:11
     * @param activity :
     * @return boolean
     */
    boolean addOrUpdateActivity(ActivityUpdateParam activity);

    /**
     * 根据id删除一个活动
     * @author wangShilei
     * @date 2020/11/30 9:12
     * @param ids :
     * @return boolean
     */
    boolean delActivity(Long[] ids);

    /**
     * 校验活动能否被修改
     * @author wangShilei
     * @date 2020/11/30 13:53
     * @param id :
     * @return 0-未开始 true
     */
    boolean checkActivity(Long[] id);


    /**
     * 校验活动能否被修改
     * @author wangShilei
     * @date 2020/11/30 13:53
     * @param id :
     * @return 0-未开始 true
     */
    boolean checkActivity(Long id);

    /**
     * 校验活动能否被修改
     * @author wangShilei
     * @date 2020/11/30 13:53
     * @param id :
     * @return 0-未开始 true
     */
    boolean checkActivity(List<Long> id);
}
