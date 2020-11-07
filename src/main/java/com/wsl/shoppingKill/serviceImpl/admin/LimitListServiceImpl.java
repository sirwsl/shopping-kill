package com.wsl.shoppingKill.serviceImpl.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.util.DateUtil;
import com.wsl.shoppingKill.convert.LimitListConverter;
import com.wsl.shoppingKill.domain.LimitList;
import com.wsl.shoppingKill.domain.LimitListParam;
import com.wsl.shoppingKill.mapper.LimitListMapper;
import com.wsl.shoppingKill.service.admin.LimitListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author WangShilei
 */
@Service
public class LimitListServiceImpl extends ServiceImpl<LimitListMapper, LimitList> implements LimitListService{

    @Resource
    private LimitListMapper limitListMapper;


    @Override
    public boolean addBlackList(LimitListParam limitListParam){
        if (limitListParam.getStartTime() == null || DateUtil.isAfter(limitListParam.getStartTime())){
            limitListParam.setStartTime(LocalDateTime.now());
        }
        if (limitListParam.getEndTime() == null){
            limitListParam.setEndTime(LocalDateTime.of(2100,12,31,0,0));
        }
        LimitList limitList = LimitListConverter.INSTANCE.limitListParam2Domain(limitListParam);

        return limitList.insert();
    }
}
