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
 *
 * 限时抢购活动表
 * @author wangshilei
 * @date 2020/11/4 16:29
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ActivityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动Id
     */
    private Long id;

    /**
     * 物品ID
     */
    private String goodsName;

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


    /**
     * 开启秒杀的SKU
     */
    private List<Goods2Sku> skuList;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Accessors(chain = true)
    public static class Goods2Sku implements Serializable{

        private Long id;

        /**
         * 物品属性
         */
        private String name;

        /**
         * 活动价格
         */
        private BigDecimal price;

        /**
         * 上架数量
         */
        private Integer totalNum;

        /**
         * 剩余数量
         */
        private Integer sellNum;
    }


}
