package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Sku;
import com.wsl.shoppingKill.obj.vo.SkuVO;

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
     * 更新sku属性
     * @param sku :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/17 16:25
     **/
    boolean updateSku(SkuVO sku);

    /**
     * 根据id删除sku
     * @param id :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/17 16:26
     **/
    boolean delSku(Long id);


}
