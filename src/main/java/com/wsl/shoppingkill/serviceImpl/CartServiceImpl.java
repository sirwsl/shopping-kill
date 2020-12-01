package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.domain.Cart;
import org.springframework.stereotype.Service;
import com.wsl.shoppingkill.mapper.CartMapper;
import com.wsl.shoppingkill.service.CartService;
/**
 * @author WangShilei
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService{

}
