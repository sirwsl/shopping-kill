package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.CommonUtil;
import com.wsl.shoppingkill.domain.Admin;
import com.wsl.shoppingkill.mapper.AdminMapper;
import com.wsl.shoppingkill.obj.bo.MailObject;
import com.wsl.shoppingkill.obj.bo.SmsObject;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.constant.RabbitMqEnum;
import com.wsl.shoppingkill.obj.constant.SmsEnum;
import com.wsl.shoppingkill.service.AdminService;
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
            description.add(admin.getNickName());
            description.add(admin.getName());
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
        final List<Admin> admins = adminMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isNotEmpty(admins)){
            admins.forEach(li -> li.setAddress(CommonUtil.replaceUserName(li.getAddress()))
                    .setIdCard(CommonUtil.replaceUserName(li.getIdCard()))
                    .setMail(CommonUtil.replaceUserName(li.getMail()))
                    .setPassword(CommonUtil.replaceUserName(li.getPassword()))
                    .setPhone(CommonUtil.replaceUserName(li.getName()))
                    .setWeChat(CommonUtil.replaceUserName(li.getWeChat()))
                    .setNickName(CommonUtil.replaceUserName(li.getNickName())));

        }
        return admins;
    }
}

