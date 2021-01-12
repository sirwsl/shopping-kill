package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.Address;
import com.wsl.shoppingkill.mapper.AddressMapper;
import com.wsl.shoppingkill.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangShilei
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService{

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Override
    public List<Address> getAddressByUser() {
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        return addressMapper.selectList(new QueryWrapper<Address>().eq(Address.USER_ID,id));
    }

    @Override
    public boolean addOrUpdateAddress(Address address) {
        address.setUserId(abstractCurrentRequestComponent.getCurrentUser().getId());
        if (address.getId() == null){
            address.setCreatTime(LocalDateTime.now()).setUpdateTime(LocalDateTime.now()).setStatus(false);
        }else {
            address.setUpdateTime(LocalDateTime.now());
        }
        return address.insertOrUpdate();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delAddress(Long id) {
        Long userId = abstractCurrentRequestComponent.getCurrentUser().getId();
        Boolean status = addressMapper.selectById(id).getStatus();

        try {
            if (Boolean.TRUE.equals(status)){
                boolean flag = addressMapper.deleteById(id)>0;
                boolean upFlag = addressMapper.selectOne(new QueryWrapper<Address>().eq(Address.USER_ID, userId).last("limit 1")).setStatus(true).updateById();
                if (flag && upFlag){
                    return true;
                }else {
                    throw new Exception();
                }
            }else {
                return addressMapper.deleteById(id)>0;
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDefaultAddress(Long id) {
        Long userId = abstractCurrentRequestComponent.getCurrentUser().getId();
        try {
            int update = addressMapper.update(new Address().setStatus(false), new QueryWrapper<Address>().eq(Address.USER_ID, userId).eq(Address.STATUS, true));
            int i = addressMapper.updateById(new Address().setStatus(true).setId(id));
            if (i>0 && update >= 0){
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }
}
