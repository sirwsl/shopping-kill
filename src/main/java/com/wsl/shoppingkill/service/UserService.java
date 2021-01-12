package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 用户基本信息获取
     * @author wangShilei
     * @date 2020/12/29 10:39 上午
     * @return com.wsl.shoppingkill.domain.User
     */
     User getUserInfo();

     /**
      * 用户更新信息
      * @author wangShilei
      * @date 2020/12/29 10:46 上午
      * @param user :
      * @return boolean
      */
    boolean updateUserInfoBySelf(User user);

    /**
     * 用户修改头像信息
     * @author wangShilei
     * @date 2020/12/29 10:57 上午
     * @param file :
     * @param response :
     * @return boolean
     */
    boolean updateUserImg(MultipartFile file, HttpServletResponse response);

    /**
     * 商城用户注册
     * @author wangShilei
     * @date 2020/12/29 9:52 下午
     * @param user :
     * @return boolean
     */
    boolean addUser(User user);

    /**
     * 用户修改密码
     * @author wangShilei
     * @date 2021/1/9 4:59 下午
     * @param old :
     * @param newPassword :
     * @param response:
     * @param request:
     * @return boolean
     */
    boolean updatePassword(String old, String newPassword,HttpServletResponse response, HttpServletRequest request);
}
