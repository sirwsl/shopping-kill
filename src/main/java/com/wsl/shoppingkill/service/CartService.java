package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Cart;
import com.wsl.shoppingkill.obj.vo.CartVO;

import java.util.List;

/**
 * @author wangShilei
 */
public interface CartService extends IService<Cart> {

    /**
     * 商品添加购物车
     * @author wangShilei
     * @date 2020/12/28 4:18 下午
     * @param skuId :
     * @return boolean
     */
    boolean addCart(Long skuId,Integer num);

    /**
     * 获取当前登录着的购物车所有商品
     * @author wangShilei
     * @date 2020/12/28 4:35 下午
     * @return java.util.List<com.wsl.shoppingkill.obj.vo.CartVO>
     */
    List<CartVO> getCartAll();

    /**
     * 根据ID删除购物车
     * @author wangShilei
     * @date 2020/12/28 4:53 下午
     * @param ids :
     * @return int
     */
    Integer delCartByIds(Long ids);
}
