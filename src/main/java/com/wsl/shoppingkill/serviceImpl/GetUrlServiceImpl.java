package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsl.shoppingkill.common.util.DateUtil;
import com.wsl.shoppingkill.domain.Activity;
import com.wsl.shoppingkill.domain.Advertise;
import com.wsl.shoppingkill.mapper.ActivityMapper;
import com.wsl.shoppingkill.obj.bo.ExposerBO;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.service.GetUrlService;
import com.wsl.shoppingkill.service.async.AsyncService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : WangShiLei
 * @date : 2020/12/31 11:10 上午
 **/
@Service
public class GetUrlServiceImpl implements GetUrlService {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private AsyncService asyncService;

    private final String SALT="3hf8qu34aal%^&543ude93#$3&*458^&*489fa|A|SD@_#O(NAM32ESAKE*$(@HFUEdan16273%^&8fjord's283";

    @Override
    public ExposerBO getUrl(Long id) {
        Object o = redisTemplate.opsForValue().get(RedisEnum.GOODS_KILL + id);
        if (Objects.isNull(o)){
            Activity activities = activityMapper.selectOne(new QueryWrapper<Activity>()
                    .le(Advertise.START_TIME, LocalDateTime.now())
                    .ge(Advertise.END_TIME, LocalDateTime.now())
                    .eq(Activity.SKU_ID,id));
            if (Objects.nonNull(activities)){
                redisTemplate.opsForValue().set(RedisEnum.GOODS_KILL_TIME+id
                        ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(activities.getStartTime()), DateUtil.distanceSecond(LocalDateTime.now(),activities.getEndTime()), TimeUnit.SECONDS);
                return new ExposerBO(getMd5(id),DateUtil.distanceSecond(LocalDateTime.now(),activities.getEndTime()));
            }
            return null;
        }
        Object time = redisTemplate.opsForValue().get(RedisEnum.GOODS_KILL_TIME + id);
        if (Objects.isNull(time)){
            asyncService.killGoodsCheck();
            return null;
        }
        LocalDateTime parse = LocalDateTime.parse(String.valueOf(time), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (parse.isAfter(LocalDateTime.now())){
            return null;
        }
        int i = Integer.parseInt(String.valueOf(o));
        if (i <= 0){
            return null;
        }
        Long expire = redisTemplate.opsForValue().getOperations().getExpire(RedisEnum.GOODS_KILL + id);
        if (expire==null || expire < 0){
            return null;
        }

        return new ExposerBO(getMd5(id),expire);
    }

    @Override
    public String getMd5(long id)
    {
        //结合秒杀的商品id与混淆字符串生成通过md5加密
        String base=id+SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

}
