package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.domain.Types;
import com.wsl.shoppingkill.mapper.TypesMapper;
import com.wsl.shoppingkill.service.TypesService;
import org.apache.commons.lang3.StringUtils;
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
        return typesMapper.insert(type) > 0;
    }

    @Override
    @MyLog(value = "#id", detail = "删除商品类别", grade = LoggerEnum.INFO)
    public boolean delTypes(Integer id) {
        return typesMapper.deleteById(id) > 0;
    }

    @Override
    @MyLog(value = "#types.id", detail = "更新商品类别", grade = LoggerEnum.NONE)
    public boolean updateTypes(Types types) {
        return typesMapper.updateById(types) > 0;
    }

    @Override
    public IPage<Types> getTypesAll(Long current, Long size) {
        return typesMapper.selectPage(new Page<>(current, size), new QueryWrapper<>());
    }

    @Override
    public IPage<Types> getTypesByNameAndId(Long current, Long size, String name, Integer id) {
        QueryWrapper<Types> select = new QueryWrapper<>();
        if (id != null) {
            select.like(Types.ID, id);
        }
        if (StringUtils.isNotBlank(name)) {
            select.like(Types.NAME, name);
        }

        return typesMapper.selectPage(new Page<>(current, size), select);
    }
}
