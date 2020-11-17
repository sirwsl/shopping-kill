package com.wsl.shoppingKill.constant;

/**
 * @author WangShilei
 * @date 2020/11/13-13:35
 **/
public class  RabbitMqEnum {


    public static class Exchange
    {

       //管理员add and remove
       public static final String EXCHANGE_ADMIN = "exchange_admin";

       //会员注册注销、通知
       public static final String EXCHANGE_USER = "exchange_user";
    }



    public static class Queue{
        //短信队列 subscription
        public static final String QUEUE_SUBSCRIPTION_MAIL = "queue_subscription_mail";

        public static final String QUEUE_SUBSCRIPTION_SMS = "queue_subscription_sms";

        //管理员
        public static final String QUEUE_ADD_ADMIN_MAIL = "queue_add_admin_mail";

        public static final String QUEUE_ADD_ADMIN_SMS = "queue_add_admin_sms";

        public static final String QUEUE_REMOVE_ADMIN_MAIL = "queue_remove_admin_mail";

        public static final String QUEUE_REMOVE_ADMIN_SMS = "queue_remove_admin_sms";

        //用户注册
        public static final String QUEUE_USER_MAIL ="queue_user_mail";

        public static final String QUEUE_USER_SMS ="queue_user_sms";

        public static final String QUEUE_UPDATE_USER_SMS ="queue_update_user_sms";

        public static final String QUEUE_UPDATE_USER_MAIL ="queue_update_user_mail";

        public static final String QUEUE_REMOVE_USER_SMS ="queue_remove_user_sms";

        public static final String QUEUE_REMOVE_USER_MAIL ="queue_remove_user_mail";

    }

    public static class Key{
        //通知
        public static final String KEY_SUBSCRIPTION_SMS = "subscription.sms";

        public static final String KEY_SUBSCRIPTION_EMAIL = "subscription.email";

        public static final String KEY_SUBSCRIPTION = "subscription.#";

        //添加-移除管理员
        public static final String KEY_ADD_ADMIN_SMS="add.admin.sms";

        public static final String KEY_ADD_ADMIN_EMAIL="add.admin.email";

        public static final String KEY_ADD_ADMIN="add.admin.#";

        public static final String KEY_REMOVE_ADMIN_SMS= "remove.admin.sms";

        public static final String KEY_REMOVE_ADMIN_EMAIL= "remove.admin.email";

        public static final String KEY_REMOVE_ADMIN= "remove.admin.#";

        //用户注册-移除
        public static final String KEY_ADD_USER_SMS = "add.user.sms";

        public static final String KEY_ADD_USER_EMAIL = "add.user.email";

        public static final String KEY_ADD_USER = "add.user.#";

        public static final String KEY_REMOVE_USER_SMS = "remove.user.sms";

        public static final String KEY_REMOVE_USER_EMAIL = "remove.user.mail";

        public static final String KEY_REMOVE_USER = "remove.user.#";

        //修改用户信息
        public static final String KEY_UPDATE_USER_SMS = "update_user_sms";

        public static final String KEY_UPDATE_USER_MAIL = "update_user_mail";


    }



}
