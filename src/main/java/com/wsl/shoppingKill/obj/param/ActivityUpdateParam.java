package com.wsl.shoppingKill.obj.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangShilei
 * @date 2020/11/30-15:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ActivityUpdateParam {

    private Long id;

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能未空")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能未空")
    private LocalDateTime endTime;

    @Valid
    @NotNull(message = "sku不能未空")
    private List<Sku> skuList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Accessors(chain = true)
    public static class Sku{

        @NotNull(message = "skuId不能为空")
        private Long id;

        @NotNull(message = "价格不能为空")
        @Min(value = 0,message = "价格不能低于0")
        private BigDecimal price;

        @NotNull(message = "数量不能为空")
        @Min(value = 0,message = "数量不能小于0")
        private Integer totalNum;
    }
}
