package com.wsl.shoppingKill.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.entity.TestEntity;

import java.util.concurrent.TimeUnit;


/**
 * @author WangShilei
 * @date 2020/10/21-13:08
 **/
public interface TestService extends IService<TestEntity> {


    boolean insertTest(TestEntity Test);

    boolean updateTest(TestEntity Test);

    TestEntity getCache(Integer id);
}
