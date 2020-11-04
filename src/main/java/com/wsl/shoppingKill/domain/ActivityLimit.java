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
 *
 * SKU在秒杀时候限制上限数量
 * @author wangshilei
 * @date 2020/11/4 16:30
 **/
@TableName("t_activity_limit")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ActivityLimit extends Model<ActivityLimit> implements Serializable {
    /**
    * id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * skuId
    */
    private Integer skuId;

    /**
    * 活动id
    */
    private Integer activityId;

    /**
    * 上限数量
    */
    private Integer limitNum;

    /**
    * 创建时间
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
    * 逻辑删除
    */
    @TableLogic
    private Boolean delFlag;
}