package com.wsl.shoppingKill.obj.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** AfterSalesVO转化类
 * @author : WangShiLei
 * @date : 2020/11/20 9:10 下午
 **/
@Mapper
public interface AfterSalesConverter {
    AfterSalesConverter INSTANCE = Mappers.getMapper(AfterSalesConverter.class);

}
