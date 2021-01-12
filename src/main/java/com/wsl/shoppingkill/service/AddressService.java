package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Address;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

/**
 * @author wangShilei
 */
public interface AddressService extends IService<Address> {

    /**
     * 获取当前登录人的收件人信息
     * @author wangShilei
     * @date 2020/12/29 1:13 下午
     * @return com.wsl.shoppingkill.domain.Address
     */
    List<Address> getAddressByUser();

    /**
     * 添加或更新收件人信息
     * @author wangShilei
     * @date 2020/12/29 1:13 下午
     * @param address :
     * @return boolean
     */
    boolean addOrUpdateAddress(Address address);

    /**
     * 根据ID删除收件人
     * @author wangShilei
     * @date 2020/12/29 1:39 下午
     * @param id :
     * @return boolean
     */
    boolean delAddress(Long id);

    /**
     * 设置默认收件地址
     * @author wangShilei
     * @date 2020/12/30 3:38 下午
     * @param id :
     * @return boolean
     */
    boolean updateDefaultAddress(Long id);
}
