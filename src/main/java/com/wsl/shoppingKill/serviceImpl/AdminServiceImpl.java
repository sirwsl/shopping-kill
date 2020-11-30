package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.obj.constant.LoggerEnum;
import com.wsl.shoppingKill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingKill.obj.constant.SmsEnum;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.mapper.AdminMapper;
import com.wsl.shoppingKill.obj.bo.MailObject;
import com.wsl.shoppingKill.obj.bo.SmsObject;
import com.wsl.shoppingKill.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{

    @Resource
    private AdminMapper adminMapper;


    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    @MyLog(detail = "添加管理员",grade = LoggerEnum.SERIOUS,value = "#admin.name")
    public boolean addAdmin(Admin admin) {
        if (adminMapper.insert(admin)>0){
            List<String> description = new ArrayList<>(8);
            description.add(admin.getName());
            description.add(admin.getPhone());
            description.add(admin.getPassword());
            SmsObject smsObject = new SmsObject();
            smsObject.setCode(SmsEnum.ADD_AUTHORIZATION.getCode())
                    .setPhone(admin.getPhone())
                    .setDescription(description);
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_SMS,smsObject);
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
            if (StringUtils.isNotBlank(admin.getPhone())){
                List<String> description = new ArrayList<>(4);
                description.add(admin.getNickName());
                description.add(admin.getName());
                SmsObject smsObject = new SmsObject();
                smsObject.setCode(SmsEnum.REMOVE_AUTHORIZATION.getCode())
                        .setPhone(admin.getPhone())
                        .setDescription(description);
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_SMS,smsObject);
            }
            if (StringUtils.isNotBlank(admin.getMail())){
                HashMap<String, String> content = new HashMap<>();
                content.put("name",admin.getNickName());
                content.put("code",admin.getName());
                content.put("phone",admin.getPhone());
                content.put("mail",admin.getMail());
                MailObject mailObject = new MailObject();
                mailObject.setTemplate("DelAdmin.flt")
                        .setNumber(admin.getMail())
                        .setSubject("取消管理授权通知")
                        .setContent(content);
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE,RabbitMqEnum.Key.KEY_NOTICE_MAIL,mailObject);
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

