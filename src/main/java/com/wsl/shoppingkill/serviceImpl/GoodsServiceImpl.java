package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.oss.OssComponent;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.mapper.GoodsMapper;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.convert.SkuConverter;
import com.wsl.shoppingkill.obj.vo.GoodsVO;
import com.wsl.shoppingkill.service.GoodsService;
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

    @Resource
    private SkuMapper skuMapper;

    String min = "?x-oss-process=image/resize,m_fill,h_50,w_50";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addGoods(Goods goods) throws Exception {
        goods.setImgUrl(ossComponent.uploadFile(BaseEnum.OSS_GOODS,goods.getFile())) ;
        return goodsMapper.insert(goods)>0;
    }

    @Override
    public IPage<GoodsVO> getGoodsAll(Long current, Long size, Goods goods) {
        IPage<GoodsVO> allGoods = goodsMapper.getAllGoods(new Page<>(current, size), goods);
        allGoods.getRecords().forEach(li->li.setImgUrl(li.getImgUrl()+min));
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
        if (flag == null || !flag){
            return goodsMapper.updateById(new Goods().setId(id).setShelf(false))>0;
        }
        return goodsMapper.updateById(new Goods().setId(id).setShelf(true))>0;
    }

    @Override
    @MyLog(value = "#goods.id", detail = "商品更新" ,grade = LoggerEnum.INFO)
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGoods(Goods goods) throws Exception {
        if (goods.getFile()!=null && StringUtils.isNotBlank(goods.getFile().getOriginalFilename())){
            goods.setImgUrl(ossComponent.uploadFile(BaseEnum.OSS_GOODS,goods.getFile()));
        }
        return goodsMapper.updateById(goods)>0;
    }

    @Override
    @MyLog(value = "#id", detail = "商品删除", grade = LoggerEnum.WORN)
    public boolean delGoods(Long id) {
        long count = skuMapper.selectList(new QueryWrapper<Sku>().eq(Sku.GOODS_ID, id)).stream().filter(li -> li.getNum() > 0).count();
        if (count > 0) {
            return false;
        }
        return goodsMapper.deleteById(id)>0;
    }
}
