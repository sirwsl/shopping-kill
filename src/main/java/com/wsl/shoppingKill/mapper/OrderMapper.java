package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsl.shoppingKill.domain.Order;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author wangShilei
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}