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
    private static final long serialVersionUID = 1L;

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
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * 是否删除
    */
    @TableLogic
    private Boolean delFlag;



    public static final String ID = "id";

    public static final String GOODS_ID = "goods_id";

    public static final String ATTRIBUTE = "attribute";

    public static final String IMG_URL = "img_url";

    public static final String REAL_PRICE = "real_price";

    public static final String COST_PRICE = "cost_price";

    public static final String SELL_PRICE = "sell_price";

    public static final String NUM = "num";

    public static final String WARN_NUM = "warn_num";

    public static final String EXP_PRICE = "exp_price";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DEL_FLAG = "del_flag";

}