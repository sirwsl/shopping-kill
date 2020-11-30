package com.wsl.shoppingKill.common.component;

import com.wsl.shoppingKill.Application;
import com.wsl.shoppingKill.common.component.email.MailComponent;
import com.wsl.shoppingKill.obj.bo.MailObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mailComponentTest {

    @Resource
    private MailComponent mailComponent;


    @Test
    public void sendSimpleMail() {
        mailComponent.sendSimpleMail("sirwsl@163.com","测试spring boot-主题","测试spring boot - 内容");
    }

    @Test
    public void testTemplateMailTest() throws MessagingException, IOException, TemplateException {
        Configuration configuration =new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader loader = Application.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader,"ftl");

        // 配置模版文件
        Template template = configuration.getTemplate("test.ftl");

        // 结合 User 对象渲染模版
        StringWriter mail = new StringWriter();
        HashMap<String,String> map = new HashMap<>();
        map.put("name","sirwsl");
        map.put("sex","男");
        template.process(map, mail);

        MailObject mailObject = new MailObject();
        mailObject.setNumber("2572396933@qq.com").setSubject("这是一封HTML模板邮件").setTemplate("test.ftl").setContent(map);
        // 将渲染结果发送出去
        mailComponent.sendMail2Html(mailObject);

    }
}

