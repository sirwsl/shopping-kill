package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 订阅者表
 * @author wangshilei
 * @date 2020/11/4 16:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends Model<Subscription> {
    /**
    * 订阅者号
    */
    private String number;

    /**
    * 订阅类型(0-手机订阅 1-邮件订阅)
    */
    private Integer type;

    /**
    * 状态(0-正常  1-取消)
    */
    private Integer status;

    /**
    * 创建时间
    */
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    private LocalDateTime updateTime;

    /**
    * 逻辑删除
    */
    private Boolean delFlag;
}