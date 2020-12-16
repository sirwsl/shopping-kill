package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.obj.vo.SkuVO;
import com.wsl.shoppingkill.obj.vo.SkuVOs;

/**
 * @author wangShilei
 */
public interface SkuService extends IService<Sku> {

    /**
     * 根据id获取一个sku
     * @param id :
     * @return sku
     * @author wangshilei
     * @date 2020/11
     * /17 16:27
     **/
    SkuVO getSku(Long id);
    /**
     * 获取所有SKU列表 与 按id或者name模糊匹配
     * @param size :
     * @param current :
     * @param id :
     * @param name :
     * @return IPage<Sku>
     * @author wangshilei
     * @date 2020/11/17 16:19
     **/
    IPage<SkuVO> getSkuAll(Long current,Long size, Long id, String name);



    /**
     * 更新sku属性或添加
     * @param sku :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/17 16:25
     **/
    boolean addSku(SkuVOs sku) throws Exception;


    boolean updateSku(SkuVO sku) throws Exception;


    /**
     * 根据id删除sku
     * @param id :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/17 16:26
     **/
    boolean delSku(Long id);

    /**
     * 根据活动id获取库存数量
     * @author wangShilei
     * @date 2020/11/30 14:20
     * @param id :
     * @return java.lang.Integer
     */
    Integer getMaxNumByActivity(Long id);


}
