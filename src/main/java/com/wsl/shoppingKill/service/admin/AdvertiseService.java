package com.wsl.shoppingKill.service.admin;

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
    IPage<Advertise> getAdvertisePage();

    /**
     * 获取图片及地址
     * @return List<AdvertiseVO>
     * @author wangshilei
     * @date 2020/11/9 18:29
     **/
    List<AdvertiseVO> getAdvertiseURL();

    /**
     * 修改活动内容
     * @param advertise:
     * @return boolean
     * @author wangshilei
     * @date 2020/11/9 18:33
     **/
    boolean updateAdvertise(Advertise advertise);

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
     * @return boolean
     * @author wangshilei
     * @date 2020/11/9 18:36
     **/
    boolean addAdvertise(Advertise advertise);


}
