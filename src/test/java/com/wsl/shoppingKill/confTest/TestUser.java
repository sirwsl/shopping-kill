package com.wsl.shoppingKill.confTest;

import com.wsl.shoppingKill.entity.TestEntity;
import com.wsl.shoppingKill.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/10/22-9:06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUser {

    @Resource
    private TestService testService;

    @Test
    public void test(){
        TestEntity testUser = new TestEntity(3,"sobriquet6","1888888888");
        System.err.println(testService.insertTest(testUser));
    }

    @Test
    public void test1(){
        System.err.println(testService.getCache(2));
    }

}
