package com.wsl.shoppingkill.serviceImpl.timedTask;

import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.service.ActivityService;
import com.wsl.shoppingkill.service.async.AsyncService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 秒杀商品定时任务
 * @author WangShilei
 * @date 2020/11/24-14:29
 **/

@Configuration
@EnableScheduling
@EnableAsync

public class KillActivityTask {

    @Resource
    private ActivityService activityService;

    @Resource
    private AsyncService asyncService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Async
    @Scheduled(cron = "0 50 * * * ?")
    public void killGoods() {
        LocalDateTime now = LocalDateTime.now().plusMinutes(20);
        List<KillGoodsVO> activityDoing = activityService.getActivityDoing(now);
        asyncService.killGoodsSync(activityDoing,now);
    }

    /**
     * 双重同步
     * @author wangShilei
     * @date 2021/1/3 7:21 下午
     * @return void
     */
    @Async
    @Scheduled(cron = "0 55 * * * ?")
    public void killGoodsCheck() {
        LocalDateTime now = LocalDateTime.now().plusMinutes(20);
        List<KillGoodsVO> activityDoing = activityService.getActivityDoing(now);
        asyncService.killGoodsSync(activityDoing,now);
    }

    /**
     * 定时刷新未来五小时商品
     * @author wangShilei
     * @date 2020/12/31 3:06 下午
     */
    @Async
    @Scheduled(cron = "0 0 * * * ?")
    public void activityFutureAsync() {
        asyncService.getActivityFuture();
    }
}
