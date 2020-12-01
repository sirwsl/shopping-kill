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
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 购物车
 * @author wangshilei
 * @date 2020/11/4 16:41
 **/
@TableName("t_cart")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Cart extends Model<Cart> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 购物车id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * 创建时间（加入时间）
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime;

    /**
    * 更新时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 状态(0-正常 1-已失效)
    */
    private Integer status;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String SKU_ID = "sku_id";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String STATUS = "status";

    public static final String DEL_FLAG = "del_flag";
}