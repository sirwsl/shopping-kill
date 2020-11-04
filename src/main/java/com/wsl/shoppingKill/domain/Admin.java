package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 管理员表
 * @author wangshilei
 * @date 2020/11/4 16:31
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Model<Admin> {
    /**
    * id
    */
    private Integer id;

    /**
    * 密码
    */
    private String password;

    /**
    * 身份证号
    */
    private String idCard;

    /**
    * 手机号
    */
    private String phone;

    /**
    * 家庭住址
    */
    private String address;

    /**
    * 微信
    */
    private String weChat;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 跟新时间
    */
    private LocalDateTime updateTime;

    /**
    * 是否离职
    */
    private Boolean delFlag;
}