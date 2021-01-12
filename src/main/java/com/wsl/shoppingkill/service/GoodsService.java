package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.obj.vo.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wangShilei
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 添加一条商品
     * @author wangShilei
     * @date 2020/11/19 10:12 下午
     * @param goods :
     * @return boolean
     * @throws Exception:
     */
    boolean addGoods(Goods goods) throws Exception;

    /**
     * 获取全部商品列表
     * @param current :
     * @param size :
     * @param goods :
     * @return IPage<Goods>
     * @author : WangShiLei
     * @date : 2020/11/19 9:55 上午
     **/
    IPage<GoodsVO> getGoodsAll(Long current, Long size,Goods goods);


    /**
     * 模糊查询商品展示页获取所有商品
     * @author wangShilei
     * @date 2020/12/23 10:56]
     * @param size :
     * @param current :
     * @param name :
     * @return List<ViewGoodsVO>
     */
    IPage<ViewGoodsVO> getViewGoodsAll(String name,Long current,Long size);


    /**
     * 商品上架处理
     * @author wangShilei
     * @date 2020/11/19 10:12 上午
     * @param id :
     * @return boolean
     */
    String merchandise(Long id);

    /**
     * 更新商品
     * @author wangShilei
     * @date 2020/11/19 10:15 上午
     * @param goods :
     * @return boolean
     * @throws Exception :
     */
    boolean updateGoods(Goods goods) throws Exception;

    /**
     * 根据ID商品某个商品
     * @author wangShilei
     * @date 2020/11/19 10:19 上午
     * @param id :
     * @return boolean
     */
    boolean delGoods(Long id);

    /**
     * 获取所有物品名称
     * @author wangShilei
     * @date 2020/12/16 9:45
     * @return List<Object>
     */
    List<BaseVO> getGoodsNameAll();


    /**
     * 获取推荐列表
     * @author wangShilei
     * @date 2020/12/22 17:47
     * @param size : 获取条数
     * @return void
     */
    List<BaseGoodsVO> getRecommendedGoods(Integer size);

    /**
     * 根据goodsId获取商品的最高价格与最低价格
     * @author wangShilei
     * @date 2020/12/22 18:53
     * @param goodsIds :
     * @return java.util.List<java.util.Map<java.lang.Long,java.util.List<java.math.BigDecimal>>>
     */
    Map<Long, BigDecimal[]> getPriceBigAndLittle(List<Long> goodsIds);

    /**
     * 根据物品ID获取物品详情
     * @author wangShilei
     * @date 2020/12/28 2:58 下午
     * @param id :
     * @param flag :
     * @return com.wsl.shoppingkill.obj.vo.GoodsDetailVO
     */
    GoodsDetailVO getGoodsDetail(Long id,Integer flag);
}
