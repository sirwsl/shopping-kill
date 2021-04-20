package com.wsl.shoppingkill.serviceImpl.adapter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsl.shoppingkill.common.util.ObjectUtil;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.vo.ViewGoodsVO;
import com.wsl.shoppingkill.service.GoodsService;
import com.wsl.shoppingkill.service.adapter.GoodsAdapter;
import com.wsl.shoppingkill.service.async.AsyncService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品适配器
 * @author WangShilei
 * @date 2020/12/24-9:38
 **/
@Service
public class GoodsAdapterImpl implements GoodsAdapter {

    @Resource
    private GoodsService goodsService;

    @Resource
    private AsyncService asyncService;

    @Resource
    private RedisTemplate<String,?> redisTemplate;

    @Override
    public IPage<ViewGoodsVO> getViewGoodsAll(String name, Long current, Long size) {
        IPage<ViewGoodsVO> goodsVO = new Page<>();
        //模糊匹配先查redis
        ObjectMapper objectMapper = new ObjectMapper();
        if (StringUtils.isNotBlank(name)) {
            String key = RedisEnum.SEARCH_GOODS + "*" + name + "*";
            Set<String> keys = redisTemplate.keys(key);
            //流水线批处理
            List<Object> objects = redisTemplate.executePipelined((RedisCallback<ViewGoodsVO>) connection -> {
                connection.openPipeline();
                for (String key1 : keys) {
                    connection.get(key1.getBytes());
                }
                return null;
            });
            if (!CollectionUtils.isEmpty(objects)){
                List<ViewGoodsVO> collect = objects.stream()
                        .map(obj -> objectMapper.convertValue(obj, ViewGoodsVO.class))
                        .collect(Collectors.toList());
                return goodsVO.setRecords(collect).setTotal(collect.size()).setSize(collect.size()).setPages(1);
            }
        }

        final IPage<ViewGoodsVO> viewGoodsAll = goodsService.getViewGoodsAll(name, current, 50L);
        if (!CollectionUtils.isEmpty(viewGoodsAll.getRecords())){
            asyncService.searchGoods(viewGoodsAll.getRecords());
            asyncService.goodsAll(viewGoodsAll);
        }
        return viewGoodsAll;
    }

    @Override
    public IPage<ViewGoodsVO> getViewGoods(Long current, Long size) {
        Object goodsVO = redisTemplate.opsForValue().get(RedisEnum.GOODS_VIEW + current);
        if (Objects.nonNull(goodsVO)){
            IPage<ViewGoodsVO> goods = ObjectUtil.castIPage(goodsVO, ViewGoodsVO.class);
            return goods;
        }
        final IPage<ViewGoodsVO> viewGoodsAll = goodsService.getViewGoodsAll(null, current, 50L);
        if (!CollectionUtils.isEmpty(viewGoodsAll.getRecords())) {
            asyncService.goodsAll(viewGoodsAll);
            asyncService.searchGoods(viewGoodsAll.getRecords());
        }
        return viewGoodsAll;
    }
}
