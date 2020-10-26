package com.wsl.shoppingKill.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {
    /**
    * id
    */
    private Long id;

    /**
    * 昵称
    */
    private String name;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码，加密存储
    */
    private String password;

    /**
    * 注册手机号
    */
    private String phone;

    /**
    * 注册邮箱
    */
    private String email;

    /**
    * 性别
    */
    private String sex;

    /**
    * 地址
    */
    private String address;

    /**
    * 头像
    */
    private String img;

    /**
    * 签名
    */
    private String description;

    /**
    * 积分
    */
    private Integer points;

    /**
    * 余额
    */
    private BigDecimal balance;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 是否注销
    */
    private Integer state;

    private static final long serialVersionUID = 1L;
}