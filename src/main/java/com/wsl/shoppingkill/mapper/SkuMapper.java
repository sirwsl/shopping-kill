package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.obj.vo.SkuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {


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
     * 获取所有SKU列表
     * @param page :
     * @param id :
     * @param name :
     * @return IPage<Sku>
     * @author wangshilei
     * @date 2020/11/17 16:19
     **/
    IPage<SkuVO> getSkuAll(Page<SkuVO> page,@Param("id") Long id, @Param("name") String name);

    /**
     * 根据活动id获取最大库存数量
     * @author wangShilei
     * @date 2020/11/30 14:12
     * @param id :
     * @return java.lang.Integer
     */
    Integer getMaxNumByActivity(Long id);

}