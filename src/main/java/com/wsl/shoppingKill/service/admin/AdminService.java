package com.wsl.shoppingKill.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author wangShilei
 */
public interface AdminService extends IService<Admin> {

    /**
     * 添加一个管理员
     * @param admin :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/9 9:58
     **/
    boolean addAdmin(Admin admin);

    /**
     * 管理员信息修改
     * @param admin :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/9 9:59
     **/
    boolean updateAdmin(Admin admin);

    /**
     * 删除一个管理员
     * @param id :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/9 10:00
     **/
    boolean delAdmin(Long id);


    /**
     * 获取管理员列表
     * @return List<Admin>
     * @author wangshilei
     * @date 2020/11/9 10:02
     **/
    List<Admin> getAdminList();


}
