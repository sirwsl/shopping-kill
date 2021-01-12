package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.util.IpUtils;
import com.wsl.shoppingkill.component.sms.SmsComponent;
import com.wsl.shoppingkill.domain.Experience;
import com.wsl.shoppingkill.mapper.ExperienceMapper;
import com.wsl.shoppingkill.obj.bo.UserBO;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.constant.SmsEnum;
import com.wsl.shoppingkill.service.ExperienceService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author : WangShiLei
 * @date : 2020/11/23 11:00 下午
 **/
@Service
public class ExperienceServiceImpl  extends ServiceImpl<ExperienceMapper, Experience> implements ExperienceService {


    @Resource
    private SmsComponent smsComponent;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, UserBO> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getExp(Experience experience, HttpServletRequest request) {
        final String ip = IpUtils.getIP(request);
        try {
            experience.setPassword(String.valueOf(ThreadLocalRandom.current().nextInt(99999999)));

            //数据库插入
            experience.setIp(ip);
            experience.insert();

            UserBO userBO = new UserBO();
            userBO.setFlag(1000);
            userBO.setId(experience.getId());
            userBO.setName(experience.getUserName());
            userBO.setUrl("https://www.wslhome.top");
            //信息存redis
            redisTemplate.opsForValue().set(RedisEnum.EXPERIENCE_LOGIN+experience.getPhone(),userBO,6, TimeUnit.HOURS);
            stringRedisTemplate.opsForValue().set(RedisEnum.EXPERIENCE_FLAG+experience.getPhone(),"体验用户666",7,TimeUnit.DAYS);
            //短信通知用户
            List<String> arr = new ArrayList<>(8);
            arr.add(experience.getUserName());
            arr.add(experience.getPhone());
            arr.add(experience.getPassword());
            smsComponent.send(SmsEnum.EXPERIENCE_USER.getCode(),arr,experience.getPhone());
            return "获取体验用户资格成功";
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "获取体验用户资格失败，请稍后再试";
        }
    }
}
