package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Order;
import com.wsl.shoppingKill.mapper.OrderMapper;
import com.wsl.shoppingKill.obj.param.OrderParam;
import com.wsl.shoppingKill.obj.vo.OrderVO;
import com.wsl.shoppingKill.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public IPage<OrderVO> getAllOrder(OrderParam orderParam, Long current, Long size){
        return orderMapper.getAllOrder(new Page<>(current,size),orderParam);
    }

    @Override
    public boolean remind2Pay(Long orderId) {
        return false;
    }

    @Override
    public boolean modifyPrice(Long orderId) {
        return false;
    }

    @Override
    public boolean outGoods(Long orderId) {
        return false;
    }

    @Override
    public boolean reminderEvaluation(Long orderId) {
        return false;
    }
}
