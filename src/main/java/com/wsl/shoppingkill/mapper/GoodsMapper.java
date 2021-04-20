package com.wsl.shoppingkill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingkill.obj.vo.BaseVO;
import com.wsl.shoppingkill.obj.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangShilei
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据查询全部或者条件查询
     *
     * @param page  :
     * @param goods :
     * @return IPage<com.wsl.shoppingkill.obj.vo.GoodsVO>
     * @author wangShilei
     * @date 2020/11/19 11:19 上午
     */
    IPage<GoodsVO> getAllGoods(Page<GoodsVO> page, @Param("goods") Goods goods);


    /**
     * 获取未开始活动的商品名称与SKU属性
     *
     * @param page :
     * @param id   :
     * @param name :
     * @return IPage<ActivityByGoodsVO>
     * @author wangShilei
     * @date 2020/11/30 9:29
     */
    IPage<ActivityByGoodsVO> getActivityBuyGoods(Page<ActivityByGoodsVO> page, @Param("id") Long id, @Param("name") String name);

    /**
     * 获取所有物品名称
     * <p>
     * Object ->(id,name)
     * </p>
     *
     * @return java.util.List<java.lang.Object>
     * @author wangShilei
     * @date 2020/12/16 10:04
     */
    List<BaseVO> getGoodsNameAll();

}