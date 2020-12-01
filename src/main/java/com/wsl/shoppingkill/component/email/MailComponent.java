package com.wsl.shoppingkill.component.email;

import com.wsl.shoppingkill.Application;
import com.wsl.shoppingkill.obj.bo.MailObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
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



    /**
     *  添加管理员账号
     * @author : WangShiLei
     * @date : 2020/11/15 11:05 下午
     * @param mailObject :信息
     **/
    public void sendMail2Html(MailObject mailObject) throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");
        Template template = configuration.getTemplate(mailObject.getTemplate());
        StringWriter mail = new StringWriter();
        template.process(mailObject.getContent(), mail);
        sendMail(mailObject.getNumber(),mailObject.getSubject(),mail.toString(),mailObject.getFile(),mailObject.getFileName());
    }


    //----------------原始内容拒接使用-------------------------------

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
     * HTML 文本邮件
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param content HTML内容
     * @throws MessagingException:发送异常
     */
    public void sendMail(String to, String subject, String content,File file,String name) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);
        if (file!=null&&file.length()>0&& StringUtils.isNotBlank(name)){
            helper.addAttachment(name,file);
        }
        mailSender.send(message);
    }


}
