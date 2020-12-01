package com.wsl.shoppingkill.service;

import com.wsl.shoppingkill.obj.param.AfterSalesParam;
import com.wsl.shoppingkill.obj.param.AfterSalesResultParam;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
class AfterSalesServiceTest {

    @Resource
    private AfterSalesService afterSalesService;

    @Test
    void getAfterSalesAll() {
        AfterSalesParam salesParam = new AfterSalesParam();

        System.err.println(afterSalesService.getAfterSalesAll(1L,10L,salesParam).getRecords().toString());
    }


    @Test
    void refundGoodsAndMoney() throws Exception {
        AfterSalesResultParam afterSalesResultParam = new AfterSalesResultParam();
        afterSalesResultParam.setId(1L).setOldSkuId(1L).setSkuId(2L).setResult(true).setResultDetail("处理测试意见")
                .setSpreadPrice(new BigDecimal("5.0"))
                .setType(2);
        System.err.println(afterSalesService.refundGoodsAndMoney(afterSalesResultParam));
    }
}