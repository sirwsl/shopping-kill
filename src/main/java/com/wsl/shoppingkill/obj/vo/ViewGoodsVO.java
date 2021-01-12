package com.wsl.shoppingkill.obj.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品展示VO
 * @author WangShilei
 * @date 2020/12/23-10:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ViewGoodsVO extends BaseGoodsVO implements Serializable {

    /**
     * 剩余数量
     */
    private Long number;

    /**
     * KUS缩略图
     */
    private List<String> skuImgUrl;

    private List<Long> skuId;


}