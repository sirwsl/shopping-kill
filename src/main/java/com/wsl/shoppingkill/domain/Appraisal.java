package com.wsl.shoppingkill.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 物品id
    */
    @NotBlank(message = "物品id不能为空")
    private String orderId;

    /**
     * 用户id
     */
    private Long userId;

    /**
    * 评价内容
    */
    @NotBlank(message = "评价内容不能为空")
    private String detail;

    /**
    * 评价图片
    */
    private String imgUrl;

    /**
    * 评价星级（1-5）
    */
    @NotNull(message = "您还没有进行星级评价")
    private Integer grade;

    /**
    * 创建时间（评价时间）
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh",pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh",pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;


    public static final String ID = "id";

    public static final String GOODS_ID = "order_id";

    public static final String USER_ID = "user_id";

    public static final String DETAIL = "detail";

    public static final String IMG_URL = "img_url";

    public static final String GRADE = "grade";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DEL_FLAG = "del_flag";
}