package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.CommonUtil;
import com.wsl.shoppingkill.component.oss.OssComponent;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.mapper.UserMapper;
import com.wsl.shoppingkill.obj.bo.MailObject;
import com.wsl.shoppingkill.obj.bo.SmsObject;
import com.wsl.shoppingkill.obj.constant.*;
import com.wsl.shoppingkill.obj.exception.ExperienceException;
import com.wsl.shoppingkill.service.LoginService;
import com.wsl.shoppingkill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author WangShilei
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private LoginService loginService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Resource
    private OssComponent ossComponent;

    @Value("${req.doMainUrl}")
    private String doMainUrl;

    @Override
    public IPage<User> getUserAll(Integer size, Integer current) {
        final IPage<User> iPage = userMapper.selectPage(new Page<>(current, size), new QueryWrapper<>());
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            iPage.getRecords().forEach(li -> li.setPassword(CommonUtil.replaceUserName(li.getPassword()))
                    .setEmail(CommonUtil.replaceUserName(li.getEmail()))
                    .setIdCard(CommonUtil.replaceUserName(li.getIdCard()))
                    .setPhone(CommonUtil.replaceUserName(li.getPhone()))
                    .setRealName(CommonUtil.replaceUserName(li.getRealName()))
                    .setWeChat(CommonUtil.replaceUserName(li.getWeChat())));
        }
        return iPage;
    }

    @Override
    @MyLog(detail = "修改会员信息", grade = LoggerEnum.INFO, value = "#user.id")
    public boolean updateUserInfo(User user) throws ExperienceException {
        try {
            if (Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                    && abstractCurrentRequestComponent.getCurrentUser().getFlag() == 1000) {
                throw new ExperienceException("体验账号权限不足");
            }
        } catch (Exception e) {
            throw new ExperienceException("体验账号权限不足");
        }
        if (userMapper.updateById(user) > 0) {
            rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE, RabbitMqEnum.Key.KEY_NOTICE_SMS,
                    getSmsObject(user, SmsEnum.UPDATE_INFO.getCode()));
            if (StringUtils.isNotBlank(user.getEmail())) {
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE, RabbitMqEnum.Key.KEY_NOTICE_MAIL,
                        getMailObject(user, "UpdateUser.ftl", "用户信息修改通知"));
            }
            return true;
        }
        return false;
    }

    @Override
    @MyLog(detail = "删除会员", grade = LoggerEnum.SERIOUS, value = "#id")
    public boolean delUserInfo(Long id) throws ExperienceException {
        try {
            if (Objects.nonNull(abstractCurrentRequestComponent.getCurrentUser()) && abstractCurrentRequestComponent.getCurrentUser().getFlag() != null
                    && abstractCurrentRequestComponent.getCurrentUser().getFlag() == 1000) {
                throw new ExperienceException("体验账号权限不足");
            }
        } catch (Exception e) {
            throw new ExperienceException("体验账号权限不足");
        }
        User user = userMapper.selectById(id);
        if (userMapper.deleteById(id) > 0) {
            if (StringUtils.isNotBlank(user.getPhone())) {
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE, RabbitMqEnum.Key.KEY_NOTICE_SMS,
                        getSmsObject(user, SmsEnum.REMOVE_AUTHORIZATION.getCode()));
            }
            if (StringUtils.isNotBlank(user.getEmail())) {
                rabbitTemplate.convertAndSend(RabbitMqEnum.Exchange.EXCHANGE_NOTICE, RabbitMqEnum.Key.KEY_NOTICE_MAIL,
                        getMailObject(user, "DelUser.flt", "删除账号通知"));
            }
        }

        return true;
    }

    @Override
    public User getUserInfo() {
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        if (id == null) {
            return null;
        }
        User user = userMapper.selectById(id);
        user.setImg(user.getImg() + "?x-oss-process=image/resize,m_fill,h_100,w_100/rounded-corners,r_50");
        return user;
    }

    @Override
    public boolean updateUserInfoBySelf(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserImg(MultipartFile file, HttpServletResponse response) {
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        try {
            String s = ossComponent.uploadFile(BaseEnum.OSS_USER, file);
            if (userMapper.updateById(new User().setId(id).setImg(s).setUpdateTime(LocalDateTime.now())) > 0) {
                Cookie img = new Cookie("img", s + "?x-oss-process=image/resize,m_fill,h_100,w_100/rounded-corners,r_50");
                img.setPath("/");
                img.setDomain(doMainUrl);
                img.setMaxAge(3600);
                response.addCookie(img);
                return true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    @MyLog(detail = "会员注册", grade = LoggerEnum.NONE)
    public boolean addUser(User user) {
        return user.insert();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(String old, String newPassword, HttpServletResponse response, HttpServletRequest request) {
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        User user = userMapper.selectById(id);
        if (Objects.nonNull(user)) {
            if (old.equals(user.getPassword())) {
                try {
                    boolean updateById = user.setPassword(newPassword).updateById();
                    if (updateById) {
                        if (loginService.exit(response, request)) {
                            return true;
                        } else {
                            throw new Exception();
                        }

                    }
                } catch (Exception e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }
        }
        return false;
    }


    /**
     * 内部方法引用
     */
    private SmsObject getSmsObject(User user, Integer type) {
        List<String> userDescription = new ArrayList<>(4);
        userDescription.add(user.getNickName());
        userDescription.add(user.getName());
        SmsObject smsObject = new SmsObject();
        smsObject.setCode(type)
                .setPhone(user.getPhone())
                .setDescription(userDescription);
        return smsObject;
    }

    private MailObject getMailObject(User user, String template, String subject) {
        HashMap<String, String> content = new HashMap<>(8);
        content.put("name", user.getNickName());
        content.put("code", user.getName());
        content.put("phone", user.getPhone());
        content.put("mail", user.getEmail());
        MailObject mailObject = new MailObject();
        mailObject.setTemplate(template)
                .setNumber(user.getEmail())
                .setSubject(subject)
                .setContent(content);
        return mailObject;
    }

}
