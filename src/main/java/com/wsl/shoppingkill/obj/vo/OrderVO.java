package com.wsl.shoppingkill.obj.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单VO
 * @author wangshilei
 * @date 2020/11/4 16:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private String id;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 买家昵称
     */
    private String nickName;

    /**
     * SKU 属性
     */
    private String attribute;

    /**
     * 物品id
     */
    private Long goodsId;

    /**
     * 物品名称
     */
    private String goodsName;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    /**
     * 支付时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    /**
     * 发货时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    /**
     * 支付金额
     */
    private BigDecimal payPrice;

    /**
     * 收件人
     */
    private String sendName;

    /**
     * 收件人手机
     */
    private String sendPhone;

    /**
     * 备注
     */
    private String remark;


}
