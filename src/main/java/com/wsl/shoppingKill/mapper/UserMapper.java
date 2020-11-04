package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsl.shoppingKill.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangShilei
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}