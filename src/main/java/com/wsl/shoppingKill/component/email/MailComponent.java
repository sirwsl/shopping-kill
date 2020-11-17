package com.wsl.shoppingKill.component.email;

import com.wsl.shoppingKill.Application;
import com.wsl.shoppingKill.component.oss.OssComponent;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.domain.SubscriptionHistory;
import com.wsl.shoppingKill.domain.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author : WangShiLei
 * @date : 2020/11/10 11:41 下午
 **/

@Component
public class MailComponent {



    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private OssComponent ossComponent;
    /**
     * 简单文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }


    /**
     *  添加管理员账号
     * @author : WangShiLei
     * @date : 2020/11/15 11:05 下午
     * @param admin :信息
     **/
    public void sendAddAdmin(Admin admin) throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");
        Template template = configuration.getTemplate("AddAdmin.ftl");
        StringWriter mail = new StringWriter();
        template.process(admin, mail);
        sendHtmlMail(admin.getMail(),"管理员账号创建成功通知",mail.toString());
    }

    /**
     *  删除管理员账号通知
     * @author : WangShiLei
     * @date : 2020/11/15 11:05 下午
     * @param admin :信息
     **/
    public void sendDelAdmin(Admin admin) throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");
        Template template = configuration.getTemplate("DelAdmin.ftl");
        StringWriter mail = new StringWriter();
        template.process(admin, mail);
        sendHtmlMail(admin.getMail(),"管理员账号移除通知",mail.toString());
    }

    /**
     *  删除会员账号通知
     * @author : WangShiLei
     * @date : 2020/11/15 11:05 下午
     * @param user :信息
     **/
    public void sendDelUser(User user) throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");
        Template template = configuration.getTemplate("DelUser.ftl");
        StringWriter mail = new StringWriter();
        template.process(user, mail);
        sendHtmlMail(user.getEmail(),"账号移除通知",mail.toString());
    }

    /**
     *  删除会员账号通知
     * @author : WangShiLei
     * @date : 2020/11/15 11:05 下午
     * @param user :信息
     **/
    public void sendUpdateUser(User user) throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");
        Template template = configuration.getTemplate("UpdateUser.ftl");
        StringWriter mail = new StringWriter();
        template.process(user, mail);
        sendHtmlMail(user.getEmail(),"账号信息修改通知",mail.toString());
    }


    /**
     *  订阅推送邮件
     * @author : WangShiLei
     * @date : 2020/11/15 11:05 下午
     * @param to: 接受者
     * @param subscriptionHistory :信息
     **/
    public void sendSubscription(String to, SubscriptionHistory subscriptionHistory) throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");
        Template template = configuration.getTemplate("SubscriptionAdmin.ftl");
        StringWriter mail = new StringWriter();
        template.process(subscriptionHistory, mail);
        sendHtmlMail(to,subscriptionHistory.getTitle(),mail.toString());
    }



    //----------------原始内容拒接使用-------------------------------

    /**
     * HTML 文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param content HTML内容
     * @throws MessagingException:发送异常
     */
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        mailSender.send(message);
    }

    /**
     * 附件邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param content HTML内容
     * @param fileUrl 附件URL
     * @param fileName 附件名称，带后缀
     * @throws MessagingException:发送异常
     */
    public void sendAttachmentsMail(String to, String subject, String content,
                                    String fileUrl,String fileName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        File file = ossComponent.getFile(fileUrl);
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }


}
