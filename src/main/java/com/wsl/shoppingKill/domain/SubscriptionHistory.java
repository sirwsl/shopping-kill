package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 订阅历史表
 * @author wangshilei
 * @date 2020/11/4 16:45
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionHistory extends Model<SubscriptionHistory> {
    /**
    * id
    */
    private Integer id;

    /**
    * 标题
    */
    private String title;

    /**
    * 订阅内容
    */
    private String detail;

    /**
    * 类型(0-手机  1-邮件)
    */
    private Integer type;

    /**
    * 发布者id
    */
    private Integer adminId;

    /**
    * 创建时间(发布时间)
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    private Boolean delFlag;
}