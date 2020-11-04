package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Sku;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wsl.shoppingKill.mapper.SkuMapper;
import com.wsl.shoppingKill.service.SkuService;
/**
 * @author WangShilei
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService{

    @Resource
    private SkuMapper skuMapper;

}
