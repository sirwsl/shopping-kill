package com.wsl.shoppingKill.component;

import com.wsl.shoppingKill.component.email.MailComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

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
    public void sendHtmlMail() throws MessagingException {

        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<img src = 'https://shopingkill.oss-cn-shenzhen.aliyuncs.com/other/e46d347b-d05b-490a-a0cd-6dba27b6209f.jpg'>"+
                "<body>\n" +
                "</html>\n";
        mailComponent.sendHtmlMail("sirwsl@163.com","这是一封HTML邮件",content);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String fileUrl = "https://shopingkill.oss-cn-shenzhen.aliyuncs.com/other/%E7%8E%8B%E4%B8%96%E7%A3%8A.pdf";
        String fileName = "test.pdf";
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<h1>附件传输</h1>\n" +
                "<body>\n" +
                "</html>\n";
        mailComponent.sendAttachmentsMail("sirwsl@163.com","这是一封HTML邮件",content, fileUrl,fileName);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {
//        Context context = new Context();
//        context.setVariable("id","ispringboot");
//
//        String emailContent = templateEngine.process("emailTeplate", context);
//        mailComponent.sendHtmlMail("ispringboot@163.com","这是一封HTML模板邮件",emailContent);

    }
}

