package com.wsl.shoppingkill.obj.convert;

import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.obj.vo.BaseGoodsVO;
import com.wsl.shoppingkill.obj.vo.KillGoodsVO;
import com.wsl.shoppingkill.obj.vo.ViewGoodsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author WangShilei
 * @date 2020/12/22-17:55
 **/
@Mapper
public interface GoodsConverter {

    GoodsConverter INSTANCE = Mappers.getMapper(GoodsConverter.class);

    /**
     * Goods转BaseGoods
     * @author wangShilei
     * @date 2020/12/22 18:00
     * @param goodsList : 商品列表
     * @return java.util.List<BaseGoodsVO>
     */
    List<BaseGoodsVO> Goods2BaseGoodsVO(List<Goods> goodsList);


    /**
     * ViewGoodsVO
     * @author wangShilei
     * @date 2020/12/22 18:00
     * @param goodsList : 商品列表
     * @return java.util.List<BaseGoodsVO>
     */
    List<ViewGoodsVO> Goods2ViewGoodsVO(List<Goods> goodsList);

    /**
     * ViewGoodsVO
     * @author wangShilei
     * @date 2020/12/22 18:00
     * @param goodsList : 商品列表
     * @return java.util.List<BaseGoodsVO>
     */
    List<KillGoodsVO> Goods2killActivityVO(List<Goods> goodsList);
}
