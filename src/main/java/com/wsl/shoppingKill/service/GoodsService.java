package com.wsl.shoppingKill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.Goods;
import com.wsl.shoppingKill.obj.vo.GoodsVO;

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
     * 商品上架处理
     * @author wangShilei
     * @date 2020/11/19 10:12 上午
     * @param id :
     * @param flag :
     * @return boolean
     */
    boolean merchandise(Long id,Boolean flag);

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


}
