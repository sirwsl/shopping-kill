package com.wsl.shoppingKill.constant;

/**
 * @author WangShilei
 * @date 2020/11/13-13:35
 **/
public class  RabbitMqEnum {


    public static class Exchange
    {

        //短信与邮件交换机
       public static final String EXCHANGE_SMS_MAIL = "exchange_sms_mail";

        //短信与邮件交换机 FANOUT类型
        public static final String EXCHANGE_FANOUT_NOTICE = "exchange_fanout_notice";

    }



    public static class Queue{
        //短信队列
        public static final String QUEUE_SMS = "queue_sms";

        //邮件队列
        public static final String QUEUE_EMAIL = "queue_email";
    }

    public static class Key{
        //短信路由键
        public static final String KEY_SMS = "#.sms";

        public static final String KEY_USER_SMS = "user.sms";

        public static final String KEY_ADMIN_SMS = "admin.sms";

        //邮箱路由键
        public static final String KEY_EMAIL = "#.email";

        public static final String KEY_USER_EMAIL = "user.email";

        public static final String KEY_ADMIN_EMAIL = "admin.email";
    }



}
