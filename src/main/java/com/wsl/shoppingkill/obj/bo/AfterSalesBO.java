package com.wsl.shoppingkill.obj.bo;

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
 * @author : WangShiLei
 * @date : 2020/11/20 9:27 下午
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AfterSalesBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private String orderId;


    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 申请内容
     */
    private String detail;

    /**
     * 处理内容
     */
    private String resultDetail;
    /**
     * 售后类型
     */
    private Integer type;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * SkuId
     */
    private Long skuId;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 支付总金额
     */
    private BigDecimal payPrice;
    /**
     * 处理时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dealTime;

    /**
     * 是否解决（0-未解决 1-已解决  默认0）
     */
    private Boolean result;
}
