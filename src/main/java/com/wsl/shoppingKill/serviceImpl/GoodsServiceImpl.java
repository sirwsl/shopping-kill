package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.entity.Goods;
import com.wsl.shoppingKill.mapper.GoodsMapper;
import com.wsl.shoppingKill.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @author wsl
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods> implements GoodsService {


}
