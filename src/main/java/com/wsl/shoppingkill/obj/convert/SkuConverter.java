package com.wsl.shoppingkill.obj.convert;

import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.obj.vo.AfterSalesVO;
import com.wsl.shoppingkill.obj.vo.GoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/11/20 12:17 上午
 **/
@Mapper
public interface SkuConverter {

    SkuConverter INSTANCE = Mappers.getMapper(SkuConverter.class);


    /**
     * 将SKU中部分属性转为GoodsVO.Sku属性
     * @author wangShilei
     * @date 2020/11/20 15:32
     * @param sku :
     * @return java.util.List<com.wsl.shoppingkill.obj.vo.GoodsVO.Sku>
     */
    List<GoodsVO.Sku> skuAllToLittle(List<Sku> sku);

    /**
     * 将SKU中部分属性转为AfterSalesVO.Sku属性
     * @author wangShilei
     * @date 2020/11/20 15:32
     * @param sku :
     * @return java.util.List<com.wsl.shoppingkill.obj.vo.GoodsVO.Sku>
     */
    List<AfterSalesVO.Sku> skuAllToLittle2(List<Sku> sku);
}
