package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 用户表
 * @author wangshilei
 * @date 2020/11/4 16:46
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {
    /**
    * id
    */
    private Integer id;

    /**
    * 账号
    */
    private String name;

    /**
    * 密码
    */
    private String password;

    /**
    * 昵称
    */
    private String nickName;

    /**
    * 头像
    */
    private String img;

    /**
    * 性别
    */
    private String sex;

    /**
    * 个性签名
    */
    private String signature;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 身份证号
    */
    private String idCard;

    /**
    * 真实姓名
    */
    private String realName;

    /**
    * 微信
    */
    private String weChat;

    /**
    * 支付宝（暂时不用）
    */
    private String apply;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 跟新时间
    */
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    private Boolean delFlag;
}