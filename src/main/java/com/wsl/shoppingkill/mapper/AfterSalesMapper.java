package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingkill.domain.AfterSales;
import com.wsl.shoppingkill.obj.param.AfterSalesParam;
import com.wsl.shoppingkill.obj.vo.AfterSalesVO;
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
     * @return IPage<com.wsl.shoppingkill.obj.vo.AfterSalesBO>
     */
    IPage<AfterSalesVO> getAfterSalesAll(Page<AfterSalesVO> page, @Param("afterSalesParam") AfterSalesParam afterSalesParam);
}