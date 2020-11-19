package com.wsl.shoppingKill.obj.convert;

import com.wsl.shoppingKill.domain.Sku;
import com.wsl.shoppingKill.obj.vo.GoodsVO;
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



    List<GoodsVO.Sku> skuAllToLittle(List<Sku> sku);
}
