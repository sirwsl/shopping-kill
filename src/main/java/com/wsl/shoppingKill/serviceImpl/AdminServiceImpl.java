package com.wsl.shoppingKill.serviceImpl;

import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Admin;
import com.wsl.shoppingKill.mapper.AdminMapper;
import com.wsl.shoppingKill.service.AdminService;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author WangShilei
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{

    @Resource
    private AdminMapper adminMapper;

    @Override
    @Cached(name = "TestUser.", key = "#admin.phone" ,expire = 1,timeUnit = TimeUnit.HOURS)
    public boolean insetAdmin(Admin admin) {
       return adminMapper.insert(admin) > 0;
    }

    @Override
    @Cached(name = "TestUser.", key = "#admin.phone" ,expire = 1,timeUnit = TimeUnit.HOURS)
    public Admin str(Admin admin){
        return new Admin();
    }

    @Override
    @Cached(name = "TestUser.", key = "#phone" ,expire = 1,timeUnit = TimeUnit.HOURS)
    public Admin getAdmin(String phone){
        System.out.println("走数据库");
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("phone",phone));
    }
}
