package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.domain.Address;
import org.springframework.stereotype.Service;
import com.wsl.shoppingkill.mapper.AddressMapper;
import com.wsl.shoppingkill.service.AddressService;
/**
 * @author WangShilei
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService{


}
