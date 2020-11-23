package com.wsl.shoppingKill.mapper;

import com.wsl.shoppingKill.obj.bo.UserBO;
import com.wsl.shoppingKill.obj.param.UserParam;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangShilei
 */
@Mapper
public interface LoginMapper {
    /**
     * 获取登录信息
     * @author wangShilei
     * @date 2020/11/23 11:04 下午
     * @param userParam :
     * @return com.wsl.shoppingKill.obj.bo.UserBO
     */
    UserBO login(UserParam userParam);
}