package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.Goods;
import com.wsl.shoppingKill.obj.vo.ActivityByGoodsVO;
import com.wsl.shoppingKill.obj.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据查询全部或者条件查询
     * @author wangShilei
     * @date 2020/11/19 11:19 上午
     * @param page :
     * @param goods :
     * @return IPage<com.wsl.shoppingKill.obj.vo.GoodsVO>
     */
    IPage<GoodsVO> getAllGoods(Page<GoodsVO> page, @Param("goods") Goods goods);


    /**
     * 获取未开始活动的商品名称与SKU属性
     * @author wangShilei
     * @date 2020/11/30 9:29
     * @param page :
     * @param id :
     * @param name :
     * @return IPage<ActivityByGoodsVO>
     */
    IPage<ActivityByGoodsVO> getActivityBuyGoods(Page<ActivityByGoodsVO> page,@Param("id") Long id,@Param("name") String name);

}