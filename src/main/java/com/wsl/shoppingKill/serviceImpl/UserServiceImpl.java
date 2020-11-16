package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.domain.User;
import com.wsl.shoppingKill.mapper.UserMapper;
import com.wsl.shoppingKill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public IPage<User> getUserAll(Integer size, Integer current) {
        return userMapper.selectPage(new Page<>(current,size),new QueryWrapper<>());
    }

    @Override
    @MyLog(detail = "修改会员信息",grade = LoggerEnum.INFO,value = "#user.id")
    public boolean updateUserInfo(User user) {
        if (userMapper.updateById(user)>0){
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_USER,RabbitMqEnum.Key.KEY_UPDATE_USER_INFO,user);
            return true;
        }
        return false;
    }

    @Override
    @MyLog(detail = "删除会员",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delUserInfo(Integer id) {
        User user = userMapper.selectById(id);
        if (userMapper.deleteById(id)>0){
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_USER,RabbitMqEnum.Key.KEY_REMOVE_ADMIN_SMS,user);
            if (StringUtils.isNotBlank(user.getEmail())){
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_USER,RabbitMqEnum.Key.KEY_REMOVE_USER_EMAIL,user);
            }
        }
        return true;
    }


}
