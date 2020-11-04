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
 * 商品表
 * @author wangshilei
 * @date 2020/11/4 16:41
 **/
@TableName("t_goods")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Goods extends Model<Goods> implements Serializable {
    /**
    * 商品id
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 商品名
    */
    private String name;

    /**
    * 类别id
    */
    private Long typeId;

    /**
    * 图片地址
    */
    private String imgUrl;

    /**
    * 是否上架
    */
    private Boolean shelf;

    /**
    * 商品描述
    */
    private String detail;

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