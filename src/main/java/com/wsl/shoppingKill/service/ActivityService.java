package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Activity;
import com.wsl.shoppingKill.obj.param.ActivityParam;
import com.wsl.shoppingKill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingKill.obj.vo.ActivityVO;

/**
 * @author wangShilei
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 获取活动列表
     * @author wangShilei
     * @date 2020/11/30 9:10
     * @param current :
     * @param size :
     * @param activityParam :
     * @return IPage<com.wsl.shoppingKill.obj.vo.ActivityVO>
     */
    IPage<ActivityVO> getActivityAll(Long current, Long size, ActivityParam activityParam);


    /**
     * 获取待添加活动列表
     * @author wangShilei
     * @date 2020/11/30 9:20
     * @param current :
     * @param size :
     * @param id :
     * @param name :
     * @return IPage<com.wsl.shoppingKill.obj.vo.ActivityByGoodsVO>
     */
    IPage<ActivityByGoodsVO> getActivityByGoods(Long current, Long size, Long id, String name);

    /**
     * 更新一个活动
     * @author wangShilei
     * @date 2020/11/30 9:11
     * @param activity :
     * @return boolean
     */
    boolean updateActivity(Activity activity);

    /**
     * 根据id删除一个活动
     * @author wangShilei
     * @date 2020/11/30 9:12
     * @param id :
     * @return boolean
     */
    boolean delActivity(Long id);

    /**
     * 关闭一个活动
     * @author wangShilei
     * @date 2020/11/30 9:15
     * @param id :
     * @return boolean
     */
    boolean closeActivity(Long id);
}
