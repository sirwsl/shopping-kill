package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.obj.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.Types;
import com.wsl.shoppingKill.mapper.TypesMapper;
import com.wsl.shoppingKill.service.TypesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types> implements TypesService {

    @Resource
    private TypesMapper typesMapper;


    @Override
    @MyLog(value = "#type.name", detail = "添加商品类别", grade = LoggerEnum.INFO)
    public boolean addTypes(Types type) {
        return typesMapper.insert(type)>0;
    }

    @Override
    @MyLog(value = "#id", detail = "删除商品类别", grade = LoggerEnum.INFO)
    public boolean delTypes(Integer id) {
        return typesMapper.deleteById(id)>0;
    }

    @Override
    @MyLog(value = "#types.id", detail = "更新商品类别", grade = LoggerEnum.NONE)
    public boolean updateTypes(Types types) {
        return typesMapper.updateById(types)>0;
    }

    @Override
    public IPage<Types> getTypesAll(Long current,Long size) {
        return typesMapper.selectPage(new Page<>(current,size),new QueryWrapper<>());
    }

    @Override
    public Types getTypesById(Integer id) {
        return typesMapper.selectById(id);
    }

    @Override
    public IPage<Types> getTypesByName(Long current,Long size,String name) {
        return typesMapper.selectPage(new Page<>(current,size),new QueryWrapper<Types>().like(Types.NAME,name));
    }
}
