package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.entity.Cart;
import com.wsl.shoppingKill.mapper.CartMapper;
import com.wsl.shoppingKill.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper,Cart> implements CartService {



}
