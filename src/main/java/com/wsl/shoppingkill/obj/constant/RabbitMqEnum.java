package com.wsl.shoppingkill.obj.constant;

/**
 * @author WangShilei
 * @date 2020/11/13-13:35
 **/
public class  RabbitMqEnum {


    public static class Exchange
    {
       //管理员add and remove
       public static final String EXCHANGE_NOTICE = "exchange_notice";

    }


    public static class Queue{
        public static final String QUEUE_NOTICE_MAIL = "queue_notice_mail";

        public static final String QUEUE_NOTICE_SMS = "queue_notice_sms";

        public static final String QUEUE_SUBSCRIPTION_MAIL = "queue_subscription_mail";

        public static final String QUEUE_SUBSCRIPTION_SMS = "queue_subscription_sms";

    }

    public static class Key{
        //通知
        public static final String KEY_NOTICE_SMS = "notice.sms";

        public static final String KEY_NOTICE_MAIL = "notice.mail";

        public static final String KEY_SUBSCRIPTION_MAIL = "key_subscription_mail";

        public static final String KEY_SUBSCRIPTION_SMS = "key_subscription_sms";
    }



}
