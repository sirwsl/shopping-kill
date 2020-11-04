package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
public interface AdminService extends IService<Admin> {
    /**
     * @param admin:
     * @return null
     * @author WangShilei
     * @date 2020/11/4 10:48 下午
     **/
    int insetAdmin(@Param("admin") Admin admin);

    /**
     *
     * @author  WangShilei
     * @date 2020/11/5 1:00 上午
     * @param id :
     * @return null
     **/
    Admin getAdmin(Long id);
}
