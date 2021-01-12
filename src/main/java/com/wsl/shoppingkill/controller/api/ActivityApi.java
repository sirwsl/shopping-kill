package com.wsl.shoppingkill.controller.api;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.service.ActivityService;
import com.wsl.shoppingkill.service.adapter.ActivityAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WangShilei
 * @date 2020/12/25-18:06
 **/
@RestController
@RequestMapping("/api")
public class ActivityApi {

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityAdapter activityAdapter;

    /**
     * 获取未来5小时内的秒杀商品
     * @author wangShilei
     * @date 2020/12/25 18:08
     * @return Result<List<KillGoodsVO>>
     */
    @GetMapping("/getKillGoods/v1")
    public Result<List<KillGoodsVO>> getKillGoodsVO(){
        return Result.success(activityService.getActivityFuture());
    }

    /**
     * 获取真正进行的活动
     * @author wangShilei
     * @date 2020/12/27 7:06 下午
     * @return Result<List <KillGoodsVO>>
     */
    @GetMapping("/getActivityDoing/v1")
    public Result<List<KillGoodsVO>> getActivityDoing(){
        return Result.success(activityAdapter.getActivityDoing());
    }

}
