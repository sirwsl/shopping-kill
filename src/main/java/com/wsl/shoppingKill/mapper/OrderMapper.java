package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.Order;
import com.wsl.shoppingKill.obj.bo.OrderMqBO;
import com.wsl.shoppingKill.obj.param.OrderParam;
import com.wsl.shoppingKill.obj.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author wangShilei
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 更具type获取订单列表
     * @author wangShilei
     * @date 2020/11/25 11:35
     * @param page :
     * @param orderParam :
     * @return IPage<com.wsl.shoppingKill.obj.vo.OrderVO>
     */
    IPage<OrderVO> getAllOrder(Page<OrderVO> page, @Param("order") OrderParam orderParam);

    /**
     * 根据id获取订单发送mq的信息
     * @author wangShilei
     * @date 2020/11/26 10:15
     * @param id :
     * @return com.wsl.shoppingKill.obj.bo.OrderMqBO
     */
    OrderMqBO getOrderSendInfo(@Param("id") Long id);
}