package com.wsl.shoppingKill.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * SKU（商品的售卖产生的影响属性）
 * @author wangshilei
 * @date 2020/11/4 16:44
 **/
@TableName("t_sku")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Sku extends Model<Sku> implements Serializable {
    /**
    * SKU_ID
    */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 商品id
    */
    private Long goodsId;

    /**
    * 商品属性
    */
    private String attribute;

    /**
    * 商品图片
    */
    private String imgUrl;

    /**
    * 进价
    */
    private BigDecimal realPrice;

    /**
    * 成本价
    */
    private BigDecimal costPrice;

    /**
    * 售价
    */
    private BigDecimal sellPrice;

    /**
    * 数量
    */
    private Integer num;

    /**
    * 预警量（默认10）
    */
    private Integer warnNum;

    /**
    * 快递费用
    */
    private BigDecimal expPrice;

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
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;
}