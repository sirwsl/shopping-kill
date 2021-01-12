package com.wsl.shoppingkill.service.async;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.obj.vo.ViewGoodsVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 异步任务执行
 * @author WangShilei
 * @date 2020/12/24-11:19
 **/

public interface AsyncService {

    /**
     * 商品搜索异步同步redis
     * @author wangShilei
     * @date 2020/12/24 11:22
     * @param list :
     * @return void
     */
    void searchGoods(List<ViewGoodsVO> list);

    /**
     * 商品分页异步执行任务
     * @author wangShilei
     * @date 2020/12/24 11:22
     * @param goods :
     * @return void
     */
    void goodsAll(IPage<ViewGoodsVO> goods);

    /**
     * 异步刷新未来五小时商品
     * @author wangShilei
     * @date 2020/12/31 3:04 下午
     */
    void getActivityFuture();

    /**
     * 正在进行秒杀商品异步执行任务
     * @author wangShilei
     * @date 2020/12/24 11:22
     * @param activityDoing :
     * @param localDateTime :
     */
    void killGoodsSync(List<KillGoodsVO> activityDoing, LocalDateTime localDateTime);

    /**
     * 同步出现问题是重新同步
     * @author wangShilei
     * @date 2021/1/3 7:44 下午
     * @return void
     */
    void killGoodsCheck();
}
