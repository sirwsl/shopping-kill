package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * 黑名单与白名单
 * @author wangshilei
 * @date 2020/11/4 16:42
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitList extends Model<LimitList> {
    /**
    * id
    */
    private Integer id;

    /**
    * 类型(0-手机号 1-ip)
    */
    private Integer type;

    /**
    * 号码
    */
    private String number;

    /**
    * 状态（0-黑名单 1-白名单）
    */
    private Integer status;

    /**
    * 开始时间
    */
    private LocalDateTime startTime;

    /**
    * 结束时间
    */
    private LocalDateTime endTime;

    /**
    * 创建时间
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