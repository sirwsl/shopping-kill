package com.wsl.shoppingKill.obj.vo;

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
import java.util.List;

/**
 * 售后记录VO
 * @author wangshilei
 * @date 2020/11/4 16:39
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class AfterSalesVO implements Serializable {
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

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户账户
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * SkuId
     */
    private Long skuId;

    /**
     * Sku属性
     */
    private String skuDetail;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 支付总金额
     */
    private BigDecimal payPrice;

    /**
     * 物品id
     */
    private Long goodsId;
    /**
     * 物品名称
     */
    private String goodsName;

    /**
     * 对应物品的SKU
     */
    private List<Sku> skuList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sku{

        private Long id;

        private String attribute;

        private Integer num;
    }


}