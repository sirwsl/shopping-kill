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
 * 订单表
 * @author wangshilei
 * @date 2020/11/4 16:43
 **/
@TableName("t_order")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Order extends Model<Order> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 订单id
    */
    @Id
    @TableId()
    private Long id;

    /**
    * 买家id
    */
    private Long userId;

    /**
    * sku_id
    */
    private Long skuId;

    /**
    * 购买数量
    */
    private Integer num;

    /**
    * 下单时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh",pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    /**
    * 支付时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    /**
    * 发货时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    /**
    * 支付类型 (0-支付宝 1-微信 2-银行...)
    */
    private Integer payType;

    /**
    * 地址id
    */
    private Long addressId;

    /**
    * 支付金额
    */
    private BigDecimal payPrice;

    /**
    * 备注
    */
    private String remark;

    /**
    * 订单状态(0-取消订单 1-已下单未支付 2-已支付 3-已出库 4-已收货 5-已评价)
    */
    private Integer status;

    /**
    * 创建时间
    */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
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

    public static final String USER_ID = "user_id";

    public static final String SKU_ID = "sku_id";

    public static final String NUM = "num";

    public static final String ORDER_TIME = "order_time";

    public static final String PAY_TIME = "pay_time";

    public static final String SEND_TIME = "send_time";

    public static final String PAY_TYPE = "pay_type";

    public static final String ADDRESS_ID = "address_id";

    public static final String PAY_PRICE = "pay_price";

    public static final String REMARK = "remark";

    public static final String STATUS = "status";

    public static final String CREAT_TIME = "creat_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DEL_FLAG = "del_flag";

}