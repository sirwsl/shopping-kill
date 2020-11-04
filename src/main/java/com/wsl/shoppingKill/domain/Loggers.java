package com.wsl.shoppingKill.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 日志记录表
 * @author wangshilei
 * @date 2020/11/4 16:42
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loggers extends Model<Loggers> {
    /**
    * 日志id
    */
    private Integer id;

    /**
    * 操作内容
    */
    private String detail;

    /**
    * 操作人id
    */
    private Integer manId;

    /**
    * 操作类型(0-用户 1-管理员)
    */
    private Integer type;

    /**
    * 等级(0-正常 1-良好 2-严重 3-极其严重)
    */
    private Integer grade;

    /**
    * 创建时间(操作时间)
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