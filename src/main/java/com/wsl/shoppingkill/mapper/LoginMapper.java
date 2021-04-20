package com.wsl.shoppingkill.mapper;

import com.wsl.shoppingkill.obj.bo.UserBO;
import com.wsl.shoppingkill.obj.param.UserParam;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangShilei
 */
@Mapper
public interface LoginMapper {
    /**
     * 获取登录信息
     *
     * @param userParam :
     * @return com.wsl.shoppingkill.obj.bo.UserBO
     * @author wangShilei
     * @date 2020/11/23 11:04 下午
     */
    UserBO login(UserParam userParam);
}