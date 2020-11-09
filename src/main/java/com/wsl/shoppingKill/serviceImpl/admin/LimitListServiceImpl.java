package com.wsl.shoppingKill.serviceImpl.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.common.util.DateUtil;
import com.wsl.shoppingKill.constant.LimitListEnum;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.convert.LimitListConverter;
import com.wsl.shoppingKill.domain.LimitList;
import com.wsl.shoppingKill.mapper.LimitListMapper;
import com.wsl.shoppingKill.obj.param.LimitListParam;
import com.wsl.shoppingKill.service.admin.LimitListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangShilei
 */
@Service
public class LimitListServiceImpl extends ServiceImpl<LimitListMapper, LimitList> implements LimitListService{

    @Resource
    private LimitListMapper limitListMapper;


    @Override
    @MyLog(detail = "添加黑名单",grade = LoggerEnum.SERIOUS,value = "#limitListParam.type")
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

    @Override
    public IPage<LimitList> getBlackListForPhone(Integer page, Integer num) {
        if (page ==null){
            page = 0;
        }
        if (num == null || num == 0){
            num = 10;
        }
        Page<LimitList> phonePage = new Page<>(page,num);
        return limitListMapper.selectPage(phonePage,new QueryWrapper<LimitList>()
                .eq(LimitList.TYPE, LimitListEnum.PHONE));
    }

    @Override
    public IPage<LimitList> getBlackListForIp(Integer page, Integer num) {
        if (page ==null){
            page = 0;
        }
        if (num == null || num == 0){
            num = 10;
        }
        Page<LimitList> phonePage = new Page<>(page,num);
        return limitListMapper.selectPage(phonePage,new QueryWrapper<LimitList>()
                .eq(LimitList.TYPE, LimitListEnum.IP));
    }

    @Override
    public List<LimitList> getBlackListByNumber(String num,Integer type) {
        return limitListMapper.selectList(new QueryWrapper<LimitList>()
                .like(LimitList.NUMBER,num)
                .eq(LimitList.TYPE,type));
    }


}
