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
    * 状态(0-正常 1-已失效)
    */
    private Integer status;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;
}