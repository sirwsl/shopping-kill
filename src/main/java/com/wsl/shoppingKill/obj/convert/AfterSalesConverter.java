package com.wsl.shoppingKill.obj.convert;

import com.wsl.shoppingKill.domain.AfterSales;
import com.wsl.shoppingKill.obj.param.AfterSalesResultParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/** AfterSalesVO转化类
 * @author : WangShiLei
 * @date : 2020/11/20 9:10 下午
 **/
@Mapper
public interface AfterSalesConverter {
    AfterSalesConverter INSTANCE = Mappers.getMapper(AfterSalesConverter.class);

    /**
     * afterSalesParam转domain
     * @author wangShilei
     * @date 2020/11/22 5:21 下午
     * @param afterSalesParam :
     * @return com.wsl.shoppingKill.domain.AfterSales
     */
    AfterSales afterSalesParam2DoMain(AfterSalesResultParam afterSalesParam);


}
