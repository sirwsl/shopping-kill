package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.User;

/**
 * @author wangShilei
 */
public interface UserService extends IService<User> {

    /**
     * 获取全部用户列表
     * @author : WangShiLei
     * @param size :
     * @param current :
     * @date : 2020/11/16 9:09 下午
     * @return IPage<User>
     **/
    IPage<User> getUserAll(Integer size,Integer current);

    /**
     * 修改用户基本信息
     * @author : WangShiLei
     * @date : 2020/11/16 9:11 下午
     * @param user:
     * @return boolean
     **/
    boolean updateUserInfo(User user);

    /**
     * 删除账户
     * @author : WangShiLei
     * @date : 2020/11/16 9:37 下午
     * @param id:
     * @return boolean
     **/
    boolean delUserInfo(Long id);


}
