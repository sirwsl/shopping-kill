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

       //订单交换机
       public static final String EXCHANGE_ORDER_KILL = "exchange_order_kill";
        //普通订单交换机
       public static final String EXCHANGE_ORDER_COMMON = "exchange_order_common";
       //普通订单交换机
       public static final String EXCHANGE_ORDER_DELAY = "exchange_order_delay";
        //死信交换机
       public static final String EXCHANGE_ORDER_DEAD = "exchange_order_dead";

       public static final String EXCHANGE_PAY = "exchange_pay";
    }


    public static class Queue{
        public static final String QUEUE_NOTICE_MAIL = "queue_notice_mail";

        public static final String QUEUE_NOTICE_SMS = "queue_notice_sms";

        public static final String QUEUE_SUBSCRIPTION_MAIL = "queue_subscription_mail";

        public static final String QUEUE_SUBSCRIPTION_SMS = "queue_subscription_sms";

        public static final String QUEUE_PAY = "queue_pay";

        //秒杀队列
        public static final String QUEUE_ORDER_KILL = "queue_order_kill";
        //普通订单队列
        public static final String QUEUE_ORDER_COMMON = "queue_order_common";
        //延迟队列
        public static final String QUEUE_ORDER_DELAY = "queue_order_delay";
        //死信队列
        public static final String QUEUE_ORDER_DEAD = "queue_order_dead";

    }

    public static class Key{
        //通知
        public static final String KEY_NOTICE_SMS = "notice.sms";

        public static final String KEY_NOTICE_MAIL = "notice.mail";

        public static final String KEY_SUBSCRIPTION_MAIL = "key_subscription_mail";

        public static final String KEY_SUBSCRIPTION_SMS = "key_subscription_sms";

        public static final String KEY_PAY = "key_pay";

        public static final String KEY_ORDER_KILL = "key_order_kill";

        public static final String KEY_ORDER_COMMON = "key_order_common";

        public static final String KEY_ORDER_DEAD = "key_order_dead";

        public static final String KEY_ORDER_DELAY = "key_order_delay";
    }



}
