package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.constant.RabbitMqEnum;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.mapper.AdminMapper;
import com.wsl.shoppingKill.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WangShilei
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{

    @Resource
    private AdminMapper adminMapper;


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    @MyLog(detail = "添加管理员",grade = LoggerEnum.SERIOUS,value = "#admin.name")
    public boolean addAdmin(Admin admin) {
        if (adminMapper.insert(admin)>0){
            if(StringUtils.isNotBlank(admin.getMail())){
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_FANOUT_NOTICE,"",admin);
            }else {
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL,RabbitMqEnum.Key.KEY_ADMIN_SMS,admin);
            }
            return true;
        }
        return false;

    }

    @Override
    @MyLog(detail = "修改管理员信息",grade = LoggerEnum.WORN,value = "#admin.name")
    public boolean updateAdmin(Admin admin) {
        return adminMapper.updateById(admin)>0;
    }

    @Override
    @MyLog(detail = "删除管理员",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delAdmin(Long id) {
        Admin admin = adminMapper.selectById(id);
        if (adminMapper.deleteById(id)>0){
            if (StringUtils.isNotBlank(admin.getMail())){
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_FANOUT_NOTICE,"",admin);
            }else {
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_SMS_MAIL,RabbitMqEnum.Key.KEY_ADMIN_SMS,admin);
            }
            return true;
        }
        return false;
    }


    @Override
    public List<Admin> getAdminList() {
        return adminMapper.selectList(new QueryWrapper<>());
    }
}

