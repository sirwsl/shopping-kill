package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Order;
import com.wsl.shoppingKill.obj.param.OrderParam;
import com.wsl.shoppingKill.obj.vo.OrderVO;

/**
 * @author wangShilei
 */
public interface OrderService extends IService<Order> {


    /**
     *  更具type获取订单列表-后台
     * @author wangShilei
     * @date 2020/11/25 11:34
     * @param orderParam :
     * @param current :
     * @param size :
     * @return IPage<com.wsl.shoppingKill.obj.vo.OrderVO>
     */
    IPage<OrderVO> getAllOrder(OrderParam orderParam, Long current, Long size);


    /**
     * 提醒支付
     * @author wangShilei
     * @date 2020/11/25 18:50
     * @param orderId :
     * @return boolean
     */
    boolean remind2Pay(Long orderId);

    /**
     * 修改价格
     * @author wangShilei
     * @date 2020/11/25 18:50
     * @param orderId :
     * @return boolean
     */
    boolean modifyPrice(Long orderId);

    /**
     * 出库短信通知
     * @author wangShilei
     * @date 2020/11/25 18:50
     * @param orderId :
     * @return boolean
     */
    boolean outGoods(Long orderId);

    /**
     * 提醒评价
     * @author wangShilei
     * @date 2020/11/25 18:49
     * @param orderId :
     * @return boolean
     */
    boolean reminderEvaluation(Long orderId);

}
