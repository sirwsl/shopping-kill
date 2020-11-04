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
 * 评价表
 * @author wangshilei
 * @date 2020/11/4 16:40
 **/
@TableName("t_appraisal")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Appraisal extends Model<Appraisal> implements Serializable {
    /**
    * id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 订单id
    */
    private String orderId;

    /**
    * 评价内容
    */
    private String detail;

    /**
    * 评价图片
    */
    private String imgUrl;

    /**
    * 评价星级（1-5）
    */
    private Integer grade;

    /**
    * 创建时间（评价时间）
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