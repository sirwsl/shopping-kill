package com.wsl.shoppingkill.serviceImpl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.oss.OssComponent;
import com.wsl.shoppingkill.domain.Advertise;
import com.wsl.shoppingkill.mapper.AdvertiseMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.convert.AdvertiseConverter;
import com.wsl.shoppingkill.obj.vo.AdvertiseVO;
import com.wsl.shoppingkill.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Resource
    private OssComponent ossComponent;

    @Value("${url.image}")
    private String target;

    @Override
    public IPage<Advertise> getAdvertisePage(Long page,Long size) {
        Page<Advertise> pages = new Page<>(page,size);
        IPage<Advertise> iPage = advertiseMapper.selectPage(pages, new QueryWrapper<>());
        if (iPage.getSize()>0) {
            iPage.setRecords(changeUrl(iPage.getRecords()));
            return iPage;
        }
        return null;
    }

    @Override
    @Cached(name = "getAdvertiseUrl",expire = 6,cacheType = CacheType.REMOTE,timeUnit = TimeUnit.HOURS)
    public List<AdvertiseVO> getAdvertiseUrl(Integer temp) {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Advertise> advertises = advertiseMapper.selectList(new QueryWrapper<Advertise>()
                .le(Advertise.START_TIME, localDateTime)
                .ge(Advertise.END_TIME, localDateTime)
        );
        if (CollectionUtils.isEmpty(advertises)){
            return null;
        }
        List<Advertise> changeAdvertise = changeUrl(advertises,false);
        return AdvertiseConverter.CONVERTER.advertise2VO(changeAdvertise);
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
            advertisePage.setRecords(changeUrl(advertisePage.getRecords()));
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
            advertisePage.setRecords(changeUrl(advertisePage.getRecords()));
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
            advertisePage.setRecords(changeUrl(advertisePage.getRecords()));
            return advertisePage;
        }

        return null;
    }

    @Override
    @MyLog(detail = "更新广告",grade = LoggerEnum.WORN,value = "#advertise.id")
    public String updateAdvertise(Advertise advertise){
        try{
            //如果更新图片需要上传
            if (!advertise.getFile().isEmpty()){
                String url = ossComponent.uploadFile(BaseEnum.OSS_ADVERTISE, advertise.getFile());
                if(url.split("/").length>2){
                    advertise.setImgUrl(url);
                    advertiseMapper.updateById(advertise);
                }else {
                    throw new Exception();
                }
            }else {
                //更新其他信息不更新图片，直接更新数据库
                if (advertise.getStartTime()==null){
                    advertise.setStartTime(LocalDateTime.now());
                }
                if (advertise.getEndTime()==null){
                    advertise.setEndTime(LocalDateTime.now().plusDays(7));
                }
                advertiseMapper.updateById(advertise);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return e.getMessage();
        }
        return "成功";
    }

    @Override
    @MyLog(detail = "删除广告",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delAdvertise(Long id) {
        return advertiseMapper.deleteById(id)>0;
    }

    @Override
    @MyLog(detail = "添加广告",grade = LoggerEnum.WORN,value = "#advertise.targetUrl")
    @Transactional(rollbackFor = Exception.class)
    public String addAdvertise(Advertise advertise){
        if (advertise.getStartTime()==null){
            advertise.setStartTime(LocalDateTime.now());
        }
        if (advertise.getEndTime()==null){
            advertise.setEndTime(LocalDateTime.now().plusDays(7));
        }

        try{

            String imgUrl = ossComponent.uploadFile(BaseEnum.OSS_ADVERTISE,advertise.getFile());
            if(imgUrl.split("/").length>2){
                advertiseMapper.insert(advertise.setImgUrl(imgUrl));
                imgUrl = "添加成功";
            }else {
                throw new Exception();
            }
            return imgUrl;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return e.getMessage();
        }
    }

    /**
     * 获取路径拼接
     * @author wangShilei
     * @date 2020/12/10 17:43
     * @param advertiseList :
     * @return java.util.List<Advertise>
     */
    private List<Advertise> changeUrl(List<Advertise> advertiseList,boolean flag){
        String min = "?x-oss-process=image/resize,m_fill,h_50,w_50";
        if (flag){
            advertiseList.forEach(li->li.setImgUrl(li.getImgUrl()+min));
        }else{
            advertiseList.forEach(li->li.setImgUrl(li.getImgUrl()));
        }
        return advertiseList;
    }

    private List<Advertise> changeUrl(List<Advertise> advertiseList){
        return changeUrl(advertiseList,true);
    }
}
