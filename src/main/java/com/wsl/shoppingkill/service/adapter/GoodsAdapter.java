package com.wsl.shoppingkill.service.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.obj.vo.ViewGoodsVO;

/**
 * @author WangShilei
 * @date 2020/12/24-9:37
 **/
public interface GoodsAdapter {


    /**
     * 模糊查询商品展示页获取所有商品
     * @author wangShilei
     * @date 2020/12/23 10:56]
     * @param size :
     * @param current :
     * @param name :
     * @return List<ViewGoodsVO>
     */
    IPage<ViewGoodsVO> getViewGoodsAll(String name, Long current, Long size);


    /**
     * 商品展示页获取所有商品
     * <p>
     *     为了加入缓存
     * </p>
     * @author wangShilei
     * @date 2020/12/23 10:56]
     * @param size :
     * @param current :
     * @return List<ViewGoodsVO>
     */
    IPage<ViewGoodsVO> getViewGoods(Long current, Long size);

}
