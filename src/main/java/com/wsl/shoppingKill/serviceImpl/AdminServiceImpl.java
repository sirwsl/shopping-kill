package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.mapper.AdminMapper;
import com.wsl.shoppingKill.service.AdminService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{

    @Resource
    private AdminMapper adminMapper;

    @Override

    @Cacheable(value = "test",key = "#admin.phone")
    public int insetAdmin(Admin admin) {
       return adminMapper.insert(admin);
    }
    @Override
    @Cacheable(value = "test",key = "#id")
    public Admin getAdmin(Long id){
        return adminMapper.selectById(id);
    }
}
