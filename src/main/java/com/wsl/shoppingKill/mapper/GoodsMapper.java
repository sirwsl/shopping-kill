package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.Goods;
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

}