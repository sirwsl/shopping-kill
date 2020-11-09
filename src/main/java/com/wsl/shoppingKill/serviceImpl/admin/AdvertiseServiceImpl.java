package com.wsl.shoppingKill.serviceImpl.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.Advertise;
import com.wsl.shoppingKill.mapper.AdvertiseMapper;
import com.wsl.shoppingKill.obj.vo.AdvertiseVO;
import org.springframework.stereotype.Service;

import com.wsl.shoppingKill.service.admin.AdvertiseService;

import java.util.List;

/**
 * @author WangShilei
 */
@Service
public class AdvertiseServiceImpl extends ServiceImpl<AdvertiseMapper, Advertise> implements AdvertiseService{


    @Override
    public IPage<Advertise> getAdvertisePage() {
        return null;
    }

    @Override
    public List<AdvertiseVO> getAdvertiseURL() {
        return null;
    }

    @Override
    @MyLog(detail = "更新广告",grade = LoggerEnum.WORN,value = "#advertise.id")
    public boolean updateAdvertise(Advertise advertise) {
        return false;
    }

    @Override
    @MyLog(detail = "删除广告",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delAdvertise(Long id) {
        return false;
    }

    @Override
    @MyLog(detail = "更新广告",grade = LoggerEnum.WORN)
    public boolean addAdvertise(Advertise advertise) {
        return false;
    }
}
