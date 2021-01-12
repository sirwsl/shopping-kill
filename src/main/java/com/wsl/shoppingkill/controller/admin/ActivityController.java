package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.param.ActivityParam;
import com.wsl.shoppingkill.obj.param.ActivityUpdateParam;
import com.wsl.shoppingkill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingkill.obj.vo.ActivityVO;
import com.wsl.shoppingkill.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 * @date 2020/11/30-10:30
 **/
@RestController
@RequestMapping("/admin")
@Slf4j
public class ActivityController {

    @Resource
    private ActivityService activityService;

    /**
     * 获取活动
     *
     * @param activityParam :
     * @return Result<List < ActivityVO>>
     * @author wangShilei
     * @date 2020/11/30 15:23
     */
    @GetMapping("/getActivityAll/v1")
    public Result<List<ActivityVO>> getActivityAll(ActivityParam activityParam) {
        if (activityParam.getStatus() == null || activityParam.getStatus() < 0) {
            return Result.error("error", "参数错误，status");
        }
        log.info("{}",activityService.getActivityAll(activityParam));
        return Result.success(activityService.getActivityAll(activityParam));
    }


    /**
     * 获取商品列表进行添加
     *
     * @param current :
     * @param size    :
     * @param id      :
     * @param name    :
     * @return Result<IPage < com.wsl.shoppingkill.obj.vo.ActivityByGoodsVO>>
     * @author wangShilei
     * @date 2020/11/30 15:23
     */
    @GetMapping("/getActivityByGoods/v1")
    public Result<IPage<ActivityByGoodsVO>> getActivityByGoods(@RequestParam(defaultValue = "1") Long current,
                                                               @RequestParam(defaultValue = "10") Long size,
                                                               Long id,
                                                               String name) {

        return Result.success(activityService.getActivityByGoods(current, size, id, name));
    }


    /**
     * 添加或者更新一个未开始活动
     *
     * @param activity :
     * @return com.wsl.shoppingkill.common.Result<java.lang.String>
     * @author wangShilei
     * @date 2020/11/30 15:24
     */
    @PostMapping("/addOrUpdateActivity/v1")
    public Result<Boolean> updateActivity(@Valid ActivityUpdateParam activity) {
        log.info(activity.toString());
        if (activity.getEndTime().isBefore(activity.getStartTime().plusHours(1))) {
            return Result.error("error", "结束时间不能小于开始时间1小时");
        }
        if (activity.getStartTime().isBefore(LocalDateTime.now().plusHours(1))) {
            return Result.error("error", "开始时间最低提前当前时1小时");
        }

        //校验能否被修改
        if( activity.getId() == null || activity.getId() < 1 ){
            //新增校验
            if (activityService.checkActivityByTime(activity)){
                return Result.error("error", "同一商品两场活动时间至少间隔12小时");
            }
        }else{
            //修改校验
            if (CollectionUtils.isNotEmpty(activity.getSkuList()) && !activityService.checkActivity(activity.getId())) {
                return Result.error("error", "当前活动不允许被修改");
            }
        }

        return Result.success(activityService.addOrUpdateActivity(activity));

    }

    /**
     * 删除一个未开始的活动
     *
     * @param id :
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/30 15:24
     */
    @DeleteMapping("/delActivity/v1")
    public Result<Boolean> delActivity(@RequestParam(value="id[]") Long[] id) {
        log.info(Arrays.toString(id));
        //0-未开始  1-进行中  2-已结束
        if (!activityService.checkActivity(id)) {
            return Result.error("error", "只能删除未开始活动");
        }
        return Result.success(activityService.delActivity(id));

    }


}
