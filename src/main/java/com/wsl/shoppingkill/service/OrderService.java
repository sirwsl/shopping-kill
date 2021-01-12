package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.obj.param.OrderParam;
import com.wsl.shoppingkill.obj.vo.OrderDetailVO;
import com.wsl.shoppingkill.obj.vo.OrderVO;
import com.wsl.shoppingkill.obj.vo.UserOrderVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
     * @return IPage<com.wsl.shoppingkill.obj.vo.OrderVO>
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
     * @param bigDecimal :
     * @return boolean
     */
    boolean modifyPrice(Long orderId, BigDecimal bigDecimal);

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

    /**
     * 获取当前用户订单统计
     * @author wangShilei
     * @date 2020/12/29 2:54 下午
     * @return java.util.Map<java.lang.String, java.lang.Integer>
     */
    Map<String,Integer> getOrderCount();

    /**
     * 获取用户订单
     * 模糊匹配名字或订单id
     * 按照类型获取
     * @author wangShilei
     * @date 2020/12/30 10:24 上午
     * @param status :
     * @param name :
     * @param size :
     * @param current :
     * @return java.util.List<UserOrderVO>
     */
    IPage<UserOrderVO> getOrderInfo(Integer status, String name,Long current,Long size);

    /**
     * 根据订单id获取订单详情
     * @author wangShilei
     * @date 2020/12/30 1:59 下午
     * @param id :
     * @return com.wsl.shoppingkill.obj.vo.OrderDetailVO
     */
    OrderDetailVO getOrderDetailVO(Long id);

    /**
     * 确认收货
     * @author wangShilei
     * @date 2021/1/1 5:28 下午
     * @param orderId :
     * @return boolean
     */
    boolean ackGoods(Long orderId);
}
