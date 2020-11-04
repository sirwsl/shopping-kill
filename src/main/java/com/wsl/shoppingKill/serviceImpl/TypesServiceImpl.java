package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Types;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.TypesMapper;
import com.wsl.shoppingKill.service.TypesService;
/**
 * @author WangShilei
 */
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types> implements TypesService {


}
