package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsl.shoppingkill.domain.Cart;
import com.wsl.shoppingkill.obj.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangShilei
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 根据用户id获取购物车列表
     * @author wangShilei
     * @date 2020/12/28 4:38 下午
     * @param userId :
     * @return java.util.List<com.wsl.shoppingkill.domain.Cart>
     */
    List<CartVO> selectCartByUserId(Long userId);
}