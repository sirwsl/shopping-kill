package com.wsl.shoppingKill.serviceImpl.admin;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.convert.AdvertiseConverter;
import com.wsl.shoppingKill.domain.Advertise;
import com.wsl.shoppingKill.mapper.AdvertiseMapper;
import com.wsl.shoppingKill.obj.vo.AdvertiseVO;
import com.wsl.shoppingKill.service.admin.AdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author WangShilei
 */
@Service
public class AdvertiseServiceImpl extends ServiceImpl<AdvertiseMapper, Advertise> implements AdvertiseService{

    @Resource
    private AdvertiseMapper advertiseMapper;


    @Override
    public IPage<Advertise> getAdvertisePage(Long page,Long size) {
        Page<Advertise> pages = new Page<>(page,size);
        IPage<Advertise> iPage = advertiseMapper.selectPage(pages, new QueryWrapper<>());
        if (iPage.getSize()>0) {
            return iPage;
        }
        return null;
    }

    @Override
    @Cached(name="advertise:",expire=1,timeUnit = TimeUnit.DAYS)
    public List<AdvertiseVO> getAdvertiseUrl() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Advertise> advertises = advertiseMapper.selectList(new QueryWrapper<Advertise>()
                .le(Advertise.START_TIME, localDateTime)
                .ge(Advertise.END_TIME, localDateTime)
        );
        if (CollectionUtils.isEmpty(advertises)){
            return null;
        }
        return AdvertiseConverter.CONVERTER.advertise2VO(advertises);
    }

    @Override
    public IPage<Advertise> getAdvertiseDoing(Long page, Long size) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Page<Advertise> pages = new Page<>(page,size);
        IPage<Advertise> advertisePage = advertiseMapper.selectPage(pages, new QueryWrapper<Advertise>()
                .le(Advertise.START_TIME, localDateTime)
                .ge(Advertise.END_TIME, localDateTime)
        );

        if (advertisePage.getSize()>0) {
            return advertisePage;
        }

        return null;

    }

    @Override
    public IPage<Advertise> getAdvertiseOver(Long page, Long size) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Page<Advertise> pages = new Page<>(page,size);
        IPage<Advertise> advertisePage = advertiseMapper.selectPage(pages, new QueryWrapper<Advertise>()
                .le(Advertise.END_TIME, localDateTime)
        );

        if (advertisePage.getSize()>0) {
            return advertisePage;
        }

        return null;
    }

    @Override
    public IPage<Advertise> getAdvertiseBegin(Long page, Long size) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Page<Advertise> pages = new Page<>(page,size);
        IPage<Advertise> advertisePage = advertiseMapper.selectPage(pages, new QueryWrapper<Advertise>()
                .ge(Advertise.START_TIME, localDateTime)
        );

        if (advertisePage.getSize()>0) {
            return advertisePage;
        }

        return null;
    }

    @Override
    @MyLog(detail = "更新广告",grade = LoggerEnum.WORN,value = "#advertise.id")
    public boolean updateAdvertise(Advertise advertise) {
        return advertiseMapper.updateById(advertise)>0;
    }

    @Override
    @MyLog(detail = "删除广告",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delAdvertise(Long id) {
        return advertiseMapper.deleteById(id)>0;
    }

    @Override
    @MyLog(detail = "添加广告",grade = LoggerEnum.WORN,value = "#advertise.targetUrl")
    public boolean addAdvertise(Advertise advertise) {
        if (advertise.getStartTime()==null){
            advertise.setStartTime(LocalDateTime.now());
        }
        if (advertise.getEndTime()==null){
            advertise.setEndTime(LocalDateTime.now().plusDays(7));
        }
        return advertiseMapper.insert(advertise)>0;
    }
}
