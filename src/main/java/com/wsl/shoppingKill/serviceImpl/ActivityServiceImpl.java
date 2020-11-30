package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.Activity;
import com.wsl.shoppingKill.mapper.ActivityMapper;
import com.wsl.shoppingKill.mapper.GoodsMapper;
import com.wsl.shoppingKill.obj.param.ActivityParam;
import com.wsl.shoppingKill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingKill.obj.vo.ActivityVO;
import com.wsl.shoppingKill.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WangShilei
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService{

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public IPage<ActivityVO> getActivityAll(Long current, Long size, ActivityParam activityParam) {
        activityParam.setTime(LocalDateTime.now());
        return activityMapper.getActivityAll(new Page<>(current,size),activityParam);
    }

    @Override
    public IPage<ActivityByGoodsVO> getActivityByGoods(Long current, Long size, Long id, String name) {
        return goodsMapper.getActivityBuyGoods(new Page<>(current,size),id,name);
    }

    @Override
    @MyLog(value = "#activity.id",detail = "更新一个活动",grade = LoggerEnum.WORN)
    public boolean updateActivity(Activity activity) {
        return activityMapper.updateById(activity)>0;
    }

    @Override
    @MyLog(value = "#activity.id",detail = "删除一个活动",grade = LoggerEnum.SERIOUS)
    public boolean delActivity(Long id) {
        return activityMapper.deleteById(id) > 0;
    }

    @Override
    @MyLog(value = "#activity.id",detail = "关闭一个活动",grade = LoggerEnum.SERIOUS)
    public boolean closeActivity(Long id) {
        Activity activity = new Activity();
        LocalDateTime time = LocalDateTime.parse("2000-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        activity.setId(id)
                .setStartTime(time)
                .setEndTime(time);
        return activityMapper.updateById(activity)>0;
    }
}
