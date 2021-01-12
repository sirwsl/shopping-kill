package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wsl.shoppingkill.domain.Order;
import com.wsl.shoppingkill.obj.bo.OrderMqBO;
import com.wsl.shoppingkill.obj.param.OrderParam;
import com.wsl.shoppingkill.obj.vo.AppraisalUserVO;
import com.wsl.shoppingkill.obj.vo.OrderDetailVO;
import com.wsl.shoppingkill.obj.vo.OrderVO;
import com.wsl.shoppingkill.obj.vo.UserOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author wangShilei
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 更具type获取订单列表
     *
     * @param page       :
     * @param orderParam :
     * @return IPage<com.wsl.shoppingkill.obj.vo.OrderVO>
     * @author wangShilei
     * @date 2020/11/25 11:35
     */
    IPage<OrderVO> getAllOrder(Page<OrderVO> page, @Param("order") OrderParam orderParam);

    /**
     * 根据id获取订单发送mq的信息
     *
     * @param id :
     * @return com.wsl.shoppingkill.obj.bo.OrderMqBO
     * @author wangShilei
     * @date 2020/11/26 10:15
     */
    OrderMqBO getOrderSendInfo(@Param("id") Long id);

    /**
     * 获取当前登录人的评价
     *
     * @param id     :
     * @param status :
     * @return java.util.List<com.wsl.shoppingkill.domain.AppraisalUserVO>
     * @author wangShilei
     * @date 2020/12/29 4:43 下午
     */
    List<AppraisalUserVO> selectGoodsInfo(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 获取用户订单 （按状态，单号物品名称模糊匹配）
     *
     * @param status :
     * @param name   :
     * @return List<UserOrderVO>
     * @author wangShilei
     * @date 2020/12/30 10:27 上午
     */
    IPage<UserOrderVO> selectUserOrderInfo(Page<UserOrderVO> page,@Param("status") Integer status, @Param("name") String name);

    /**
     * 根据ID获取订单详情
     *
     * @return OrderDetailVO
     * @author wangShilei
     * @date 2020/12/30 2:04 下午
     */
    OrderDetailVO selectOrderDetail(@Param("id") Long id);
}