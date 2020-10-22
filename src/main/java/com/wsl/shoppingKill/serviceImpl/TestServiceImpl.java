package com.wsl.shoppingKill.serviceImpl;

import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.entity.TestEntity;
import com.wsl.shoppingKill.mapper.TestMapper;
import com.wsl.shoppingKill.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author WangShilei
 * @date 2020/10/21-13:11
 **/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,TestEntity> implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    @Cached(name = "TestUser.", key = "#user.id",expire = 1,timeUnit = TimeUnit.HOURS)
    public boolean insertTest(TestEntity user) {
        return testMapper.insert(user) > 0;
    }

    @Override
    @Cached(name ="TestUser.",key = "#id" ,expire = 1, timeUnit = TimeUnit.HOURS)
    public TestEntity getCache(Integer id) {
        return testMapper.selectById(id);
    }

    @Override
    @CacheUpdate(name = "TestUser.", key = "#user.id", value = "#user" )
    public boolean updateTest(TestEntity user){
        return testMapper.updateById(user) >0;
    }

}
