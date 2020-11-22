package com.wsl.shoppingKill.obj.param;

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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class AfterSalesResultParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @NotNull(message = "id不能未空")
    private Long id;

    /**
    * 订单id
    */
    @NotNull(message = "原SKUId不能未空")
    private Long oldSkuId;

    /**
     * 换货SkuId
     */
    private Long skuId;

    /**
     * 差价
     */
    private BigDecimal spreadPrice;

    /**
     * 处理内容
     */
    @NotNull(message = "处理内容不能未空")
    private String resultDetail;

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
    @NotNull(message = "处理结果不能未空")
    private Boolean result;

    /**
     * 处理类型
     */
    @NotNull(message = "处理类型不能为空")
    private Integer type;

    /**
     * 管理员id
     */
    private Long adminId;


}