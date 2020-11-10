package com.wsl.shoppingKill.obj.convert;

import com.wsl.shoppingKill.domain.LimitList;
import com.wsl.shoppingKill.obj.param.LimitListParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** 限制名单转化类
 * @author : WangShiLei
 * @date : 2020/11/7 4:39 下午
 **/
@Mapper
public interface LimitListConverter {

    LimitListConverter INSTANCE = Mappers.getMapper(LimitListConverter.class);

    /**
     * 名单限制Param转domain
     * @author  WangShilei
     * @date 2020/11/7 4:46 下午
     * @param listParam : param实体
     * @return LimitList ： domain实体
     **/
    LimitList limitListParam2Domain(LimitListParam listParam);
}
