package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.constant.SmsEnum;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.mapper.UserMapper;
import com.wsl.shoppingkill.obj.bo.MailObject;
import com.wsl.shoppingkill.obj.bo.SmsObject;
import com.wsl.shoppingkill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_SMS,
                    getSmsObject(user,SmsEnum.UPDATE_INFO.getCode()));
            if (StringUtils.isNotBlank(user.getEmail())){
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_MAIL,
                        getMailObject(user,"UpdateUser.ftl","用户信息修改通知"));
            }
            return true;
        }
        return false;
    }

    @Override
    @MyLog(detail = "删除会员",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delUserInfo(Long id) {
        User user = userMapper.selectById(id);
        if (StringUtils.isNotBlank(user.getPhone())){
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_SMS,
                    getSmsObject(user,SmsEnum.REMOVE_AUTHORIZATION.getCode()));
        }
        if (StringUtils.isNotBlank(user.getEmail())){
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_MAIL,
                    getMailObject(user,"DelUser.flt","删除账号通知"));
        }

        return true;
    }


    /**
     * 内部方法引用
     */
    private SmsObject getSmsObject(User user,Integer type){
        List<String> userDescription = new ArrayList<>(4);
        userDescription.add(user.getNickName());
        userDescription.add(user.getName());
        SmsObject smsObject = new SmsObject();
        smsObject.setCode(type)
                .setPhone(user.getPhone())
                .setDescription(userDescription);
        return smsObject;
    }

    private MailObject getMailObject(User user,String template,String subject){
        HashMap<String, String> content = new HashMap<>(8);
        content.put("name",user.getNickName());
        content.put("code",user.getName());
        content.put("phone",user.getPhone());
        content.put("mail",user.getEmail());
        MailObject mailObject = new MailObject();
        mailObject.setTemplate(template)
                .setNumber(user.getEmail())
                .setSubject(subject)
                .setContent(content);
        return mailObject;
    }

}
