package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.entity.Order;
import com.wsl.shoppingKill.mapper.OrderMapper;
import com.wsl.shoppingKill.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author wsl
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


}
