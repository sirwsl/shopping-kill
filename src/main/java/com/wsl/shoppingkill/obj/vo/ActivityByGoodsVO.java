package com.wsl.shoppingkill.obj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class ActivityByGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 物品Id
     */
    private Long id;

    /**
     * 物品ID
     */
    private String goodsName;


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
         * 上架数量
         */
        private Integer num;
    }


}
