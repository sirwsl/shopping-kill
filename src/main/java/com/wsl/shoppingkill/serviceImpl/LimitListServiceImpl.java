package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.common.util.DateUtil;
import com.wsl.shoppingkill.domain.LimitList;
import com.wsl.shoppingkill.mapper.LimitListMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.convert.LimitListConverter;
import com.wsl.shoppingkill.obj.param.LimitListParam;
import com.wsl.shoppingkill.service.LimitListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author WangShilei
 */
@Service
public class LimitListServiceImpl extends ServiceImpl<LimitListMapper, LimitList> implements LimitListService {

    @Resource
    private LimitListMapper limitListMapper;


    @Override
    @MyLog(detail = "添加黑名单", grade = LoggerEnum.SERIOUS, value = "#limitListParam.number")
    public boolean addBlackList(LimitListParam limitListParam) {
        if (limitListParam.getStartTime() == null || DateUtil.isAfter(limitListParam.getStartTime())) {
            limitListParam.setStartTime(LocalDateTime.now());
        }
        if (limitListParam.getEndTime() == null) {
            limitListParam.setEndTime(LocalDateTime.of(2100, 12, 31, 0, 0));
        }
        LimitList limitList = LimitListConverter.INSTANCE.limitListParam2Domain(limitListParam);

        return limitList.insert();
    }

    @Override
    public IPage<LimitList> getBlackListForPhone(Integer page, Integer num) {
        if (page == null) {
            page = 0;
        }
        if (num == null || num == 0) {
            num = 10;
        }
        Page<LimitList> phonePage = new Page<>(page, num);
        return limitListMapper.selectPage(phonePage, new QueryWrapper<LimitList>()
                .eq(LimitList.TYPE, BaseEnum.PHONE));
    }

    @Override
    public IPage<LimitList> getBlackListForIp(Integer page, Integer num) {
        if (page == null) {
            page = 0;
        }
        if (num == null || num == 0) {
            num = 10;
        }
        Page<LimitList> phonePage = new Page<>(page, num);
        return limitListMapper.selectPage(phonePage, new QueryWrapper<LimitList>()
                .eq(LimitList.TYPE, BaseEnum.IP));
    }

    @Override
    public IPage<LimitList> getBlackListByNumber(Long page, Long num, String number, Integer type) {
        return limitListMapper.selectPage(new Page<>(page, num), new QueryWrapper<LimitList>()
                .like(LimitList.NUMBER, number)
                .eq(LimitList.TYPE, type));
    }


}
