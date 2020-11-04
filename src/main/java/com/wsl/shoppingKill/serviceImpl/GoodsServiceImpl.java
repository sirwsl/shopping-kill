package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Goods;
import org.springframework.stereotype.Service;
import com.wsl.shoppingKill.mapper.GoodsMapper;
import com.wsl.shoppingKill.service.GoodsService;
/**
 * @author WangShilei
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService{


}
