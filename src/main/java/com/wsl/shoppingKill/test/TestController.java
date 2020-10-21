package com.wsl.shoppingKill.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wangshilei
 * @date 2020/10/21 11:43
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {


    @Resource
    private CuratorFramework curatorFramework;

    private String lockPath = "/lock/test/";


    @GetMapping(value = "/zookpTest", produces = "application/json; charset=utf-8")
    public void zookpTest() {
        String lockName = lockPath + UUID.randomUUID().toString();
        log.info("============={} 线程访问开始=========lockName:{}",Thread.currentThread().getName(),lockName);
        //TODO 获取分布式锁
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex (curatorFramework, lockName);
        try{
            //获取锁资源
            boolean flag = lock.acquire(10, TimeUnit.HOURS);
            if(flag){
                log.info("线程:{}，获取到了锁",Thread.currentThread().getName());
                //TODO 获得锁之后可以进行相应的处理  睡一会
                Thread.sleep(500);
                log.info("======获得锁后进行相应的操作======" + Thread.currentThread().getName());
            }
        }catch (Exception e){
            log.info("错误信息：{}",e.getMessage());
        }finally {
            try {
                lock.release();
                log.info("=========lockName:{}==============={}释放了锁",lockName,Thread.currentThread().getName());
            } catch (Exception e) {
                log.info("错误信息：{}",e.getMessage());
            }
        }

    }

}