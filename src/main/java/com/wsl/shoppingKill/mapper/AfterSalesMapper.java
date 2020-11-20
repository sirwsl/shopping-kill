package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.AfterSales;
import com.wsl.shoppingKill.obj.bo.AfterSalesBO;
import com.wsl.shoppingKill.obj.param.AfterSalesParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
@Mapper
public interface AfterSalesMapper extends BaseMapper<AfterSales> {

    /**
     * 获取售后订单
     * 根据状态1-已处理 2未处理获取
     * 根据类型3-退货退款 2-换货 1-退款
     * 根据单号 根据id
     * @author wangShilei
     * @date 2020/11/20 15:30
     * @param page :
     * @param afterSalesParam :
     * @return IPage<com.wsl.shoppingKill.obj.vo.AfterSalesBO>
     */
    IPage<AfterSalesBO> getAfterSalesAll(Page<AfterSalesBO> page, @Param("afterSalesParam") AfterSalesParam afterSalesParam);
}