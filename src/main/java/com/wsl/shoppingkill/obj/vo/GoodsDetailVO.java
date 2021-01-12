package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/12/28 2:47 下午
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class GoodsDetailVO implements Serializable {
    /**
     * 物品id
     */
    private Long id;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 评价人数
     */
    private Integer evalNum;

    private List<Sku> skuList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Accessors(chain = true)
    public static class Sku implements Serializable{
        //Sku ID
        private Long id;
        //sku name
        private String name;

        //sku 价格
        private BigDecimal price;

        //剩余数量
        private Integer total;

        //运费
        private BigDecimal expPrice;

        private String imgUrl;
    }
}
