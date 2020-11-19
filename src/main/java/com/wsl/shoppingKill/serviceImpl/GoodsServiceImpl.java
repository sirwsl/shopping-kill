package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.common.log.MyLog;
import com.wsl.shoppingKill.component.oss.OssComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.constant.LoggerEnum;
import com.wsl.shoppingKill.domain.Goods;
import com.wsl.shoppingKill.domain.Sku;
import com.wsl.shoppingKill.mapper.GoodsMapper;
import com.wsl.shoppingKill.obj.convert.SkuConverter;
import com.wsl.shoppingKill.obj.vo.GoodsVO;
import com.wsl.shoppingKill.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService{

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private OssComponent ossComponent;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addGoods(Goods goods) throws Exception {
        goods.setImgUrl(ossComponent.uploadFile(BaseEnum.OSS_GOODS,goods.getFile())) ;
        return goodsMapper.insert(goods)>0;
    }

    @Override
    public IPage<GoodsVO> getGoodsAll(Long current, Long size, Goods goods) {
        IPage<GoodsVO> allGoods = goodsMapper.getAllGoods(new Page<>(current, size), goods);
        Sku sku = new Sku();
        Map<Long, List<Sku>> collect = sku.selectList(new QueryWrapper<Sku>().in(Sku.GOODS_ID,
                allGoods.getRecords()
                        .stream()
                        .map(GoodsVO::getId)
                        .collect(Collectors.toList()))
                .select(Sku.ID, Sku.GOODS_ID, Sku.ATTRIBUTE, Sku.NUM))
                .stream()
                .collect(Collectors.groupingBy(Sku::getGoodsId));
        allGoods.getRecords().forEach(li -> li.setSkuList(SkuConverter.INSTANCE.skuAllToLittle(collect.get(li.getId()))));
        return allGoods;
    }


    @Override
    @MyLog(value = "#id", detail = "商品上架处理", grade = LoggerEnum.INFO)
    public boolean merchandise(Long id,Boolean flag) {
        if (!flag){
            return goodsMapper.updateById(new Goods().setShelf(false))>0;
        }
        return goodsMapper.updateById(new Goods().setShelf(true))>0;
    }

    @Override
    @MyLog(value = "#goods.id", detail = "商品更新" ,grade = LoggerEnum.INFO)
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGoods(Goods goods) throws Exception {
        if (StringUtils.isNotBlank(goods.getFile().getOriginalFilename())){
            ossComponent.uploadFile(BaseEnum.OSS_GOODS,goods.getFile());
        }
        return goodsMapper.updateById(goods)>0;
    }

    @Override
    @MyLog(value = "#id", detail = "商品删除", grade = LoggerEnum.WORN)
    public boolean delGoods(Long id) {
        return goodsMapper.deleteById(id)>0;
    }
}
