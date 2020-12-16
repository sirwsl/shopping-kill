package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Types;

/**
 * @author wangShilei
 */
public interface TypesService extends IService<Types> {

    /**
     * 添加商品类别
     * @author wangShilei
     * @date 2020/11/18 16:21
     * @param type :
     * @return boolean
     */
    boolean addTypes(Types type);
    
    /**
     * 删除商品类别
     * @author wangShilei
     * @date 2020/11/18 16:22
     * @param id : 
     * @return boolean
     */
    boolean delTypes(Integer id);

    /**
     * 更新类别名称
     * @author wangShilei
     * @date 2020/11/18 17:10
     * @param types :
     * @return boolean
     */
    boolean updateTypes(Types types);

    /**
     * 获取全部分类
     * @author wangShilei
     * @date 2020/11/18 16:43
     * @param current :
     * @param size :
     * @return IPage<com.wsl.shoppingkill.domain.Types>
     */
    IPage<Types> getTypesAll(Long current,Long size);

    
    /**
     * 更具名字模糊查询全部分类
     * @author wangShilei
     * @date 2020/11/18 16:43
     * @param current :
     * @param size :
     * @param name :
     * @param id :
     * @return IPage<com.wsl.shoppingkill.domain.Types>
     */
    IPage<Types> getTypesByNameAndId(Long current,Long size,String name,Integer id);
}
