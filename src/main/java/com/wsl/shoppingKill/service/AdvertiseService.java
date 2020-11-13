package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Advertise;
import com.wsl.shoppingKill.obj.vo.AdvertiseVO;

import java.util.List;

/**
 * @author wangShilei
 */
public interface AdvertiseService extends IService<Advertise> {

    /**
     * 获取活动列表
     * @return null
     * @author wangshilei
     * @date 2020/11/9 18:28
     **/
    IPage<Advertise> getAdvertisePage(Long page,Long size);

    /**
     * 获取图片及地址
     * @return List<AdvertiseVO>
     * @author wangshilei
     * @date 2020/11/9 18:29
     **/
    List<AdvertiseVO> getAdvertiseUrl();

    /**
     * 获取正在展示的广告
     * @author : WangShiLei
     * @date : 2020/11/9 10:04 下午
     * @param page:
     * @param size：
     * @return IPage
     **/
    IPage<Advertise> getAdvertiseDoing(Long page, Long size);


    /**
     * 获取已经结束展示的广告
     * @author : WangShiLei
     * @date : 2020/11/9 10:04 下午
     * @param page:
     * @param size :
     * @return IPage
     **/
    IPage<Advertise> getAdvertiseOver(Long page, Long size);

    /**
     * 获取未开始展示的广告
     * @author : WangShiLei
     * @date : 2020/11/9 10:04 下午
     * @param page:
     * @param size :
     * @return IPage
     **/
    IPage<Advertise> getAdvertiseBegin(Long page, Long size);


    /**
     * 修改活动内容
     * @param advertise:
     * @return String
     * @author wangshilei
     * @date 2020/11/9 18:33
     **/
    String updateAdvertise(Advertise advertise);

    /**
     * 删除一个活动
     * @param id :
     * @return null
     * @author wangshilei
     * @date 2020/11/9 18:35
     **/
    boolean delAdvertise(Long id);

    /**
     * 添加一个活动
     * @param advertise :
     * @return String
     * @author wangshilei
     * @date 2020/11/9 18:36
     **/
    String addAdvertise(Advertise advertise);


}
