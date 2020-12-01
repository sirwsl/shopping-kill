package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsl.shoppingkill.domain.Cart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangShilei
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}