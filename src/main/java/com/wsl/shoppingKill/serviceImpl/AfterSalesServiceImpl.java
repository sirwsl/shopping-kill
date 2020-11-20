package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.AfterSales;
import com.wsl.shoppingKill.mapper.AfterSalesMapper;
import com.wsl.shoppingKill.obj.param.AfterSalesParam;
import com.wsl.shoppingKill.obj.param.AfterSalesResultParam;
import com.wsl.shoppingKill.obj.vo.AfterSalesVO;
import com.wsl.shoppingKill.service.AfterSalesService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class AfterSalesServiceImpl extends ServiceImpl<AfterSalesMapper, AfterSales> implements AfterSalesService{

    @Resource
    private AfterSalesMapper afterSalesMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public IPage<AfterSalesVO> getAfterSalesAll(Long current, Long size, AfterSalesParam afterSalesParam) {
        //TODO：获取售后所有结果
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @MyLog(value = "#afterSalesResultParam.id",detail = "售后处理",grade = LoggerEnum.INFO)
    public boolean refundGoodsAndMoney(AfterSalesResultParam afterSalesResultParam) {
        //TODO 售后处理
        return false;
    }
}
