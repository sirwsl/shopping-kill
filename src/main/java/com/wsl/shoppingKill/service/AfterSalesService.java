package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.AfterSales;
import com.wsl.shoppingKill.obj.param.AfterSalesParam;
import com.wsl.shoppingKill.obj.param.AfterSalesResultParam;
import com.wsl.shoppingKill.obj.vo.AfterSalesVO;

/**
 * @author wangShilei
 */
public interface AfterSalesService extends IService<AfterSales> {

    /**
     * 获取售后订单
     * 根据状态1-已处理 2未处理获取
     * 根据类型3-退货退款 2-换货 1-退款
     * 根据单号 用户name 用户phone 用户昵称nick_name查询
     * 根据id
     * @param current :
     * @param size :
     * @param afterSalesParam :
     * @return IPage<AfterSalesVO>
     */
    IPage<AfterSalesVO> getAfterSalesAll(Long current, Long size, AfterSalesParam afterSalesParam);

    /**
     * 退货退款/换货/退款
     * @author wangShilei
     * @date 2020/11/20 15:23
     * @param afterSalesResultParam :
     * @return boolean
     */
    boolean refundGoodsAndMoney(AfterSalesResultParam afterSalesResultParam) throws Exception;

}
