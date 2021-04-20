package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.Cart;
import com.wsl.shoppingkill.mapper.CartMapper;
import com.wsl.shoppingkill.obj.vo.CartVO;
import com.wsl.shoppingkill.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangShilei
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Override
    public boolean addCart(Long skuId, Integer num) {
        Long userId = abstractCurrentRequestComponent.getCurrentUser().getId();
        Cart cart = new Cart();
        return cart.setCreatTime(LocalDateTime.now()).setUpdateTime(LocalDateTime.now())
                .setUserId(userId).setSkuId(skuId).setNum(num).insert();

    }

    @Override
    public List<CartVO> getCartAll() {
        Long userId = abstractCurrentRequestComponent.getCurrentUser().getId();
        List<CartVO> cartVOS = cartMapper.selectCartByUserId(userId);
        cartVOS.forEach(li -> li.setTotalPrice(li.getPrice().multiply(new BigDecimal(li.getNum()))));
        return cartVOS;
    }

    @Override
    public Integer delCartByIds(Long ids) {
        Long userId = abstractCurrentRequestComponent.getCurrentUser().getId();

        return cartMapper.delete(new QueryWrapper<Cart>()
                .eq(Cart.USER_ID, userId)
                .eq(Cart.ID, ids));

    }
}
