package com.wsl.shoppingKill.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志记录表
 * @author wangshilei
 * @date 2020/11/4 16:42
 **/
@TableName("t_loggers")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Loggers extends Model<Loggers> implements Serializable {
    /**
    * 日志id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 操作内容
    */
    private String detail;

    /**
    * 操作人id
    */
    private Long manId;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;
}