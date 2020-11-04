package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Cart;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.CartMapper;
import com.wsl.shoppingKill.service.CartService;
/**
 * @author WangShilei
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService{

}
