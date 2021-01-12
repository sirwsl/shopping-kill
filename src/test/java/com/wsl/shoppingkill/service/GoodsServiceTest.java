package com.wsl.shoppingkill.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
class GoodsServiceTest {

    @Resource
    GoodsService goodsService;

    @Test
    void getViewGoodsAll() {
        goodsService.getViewGoodsAll(null, 1L, 10L).getRecords().forEach(System.out::println);
    }
}