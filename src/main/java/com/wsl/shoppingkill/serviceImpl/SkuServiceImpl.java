package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.component.oss.OssComponent;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.vo.SkuVO;
import com.wsl.shoppingkill.obj.vo.SkuVOs;
import com.wsl.shoppingkill.service.SkuService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WangShilei
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private OssComponent ossComponent;

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public SkuVO getSku(Long id) {
        return skuMapper.getSku(id);
    }

    @Override
    public IPage<SkuVO> getSkuAll(Long current, Long size, Long id, String name) {
        IPage<SkuVO> skuAll = skuMapper.getSkuAll(new Page<>(current, size), id, name);
        skuAll.setRecords(changeUrl(skuAll.getRecords()));
        return skuAll;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean updateSku(SkuVO skuVO) throws Exception {
        Sku sku = new Sku();
        Goods goods = new Goods();
        goods.setId(skuVO.getId()).setName(skuVO.getGoodsName()).updateById();
        if (!skuVO.getImg().isEmpty()) {
            skuVO.setImgUrl(ossComponent.uploadFile(BaseEnum.OSS_SKU, skuVO.getImg()));
        }
        sku.setWarnNum(skuVO.getWarnNum())
                .setSellPrice(skuVO.getSellPrice())
                .setRealPrice(skuVO.getRealPrice())
                .setImgUrl(skuVO.getImgUrl())
                .setAttribute(skuVO.getAttribute())
                .setCostPrice(skuVO.getCostPrice())
                .setExpPrice(skuVO.getExpPrice())
                .setId(skuVO.getId())
                .setNum(skuVO.getNum())
                .setGoodsId(skuVO.getGoodsId())
                .setUpdateTime(LocalDateTime.now())
                .insertOrUpdate();
        redisTemplate.opsForValue().set(RedisEnum.GOODS_SKU_NUM + sku.getId(), sku.getNum());
        return true;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean addSku(SkuVOs skuVO) throws Exception {
        Sku sku = new Sku();
        if (!skuVO.getImgs().isEmpty()) {
            skuVO.setImgUrls(ossComponent.uploadFile(BaseEnum.OSS_SKU, skuVO.getImgs()));
        }
        sku.setWarnNum(skuVO.getWarnNums())
                .setSellPrice(skuVO.getSellPrices())
                .setRealPrice(skuVO.getRealPrices())
                .setImgUrl(skuVO.getImgUrls())
                .setAttribute(skuVO.getAttributes())
                .setCostPrice(skuVO.getCostPrices())
                .setExpPrice(skuVO.getExpPricess())
                .setNum(skuVO.getNums())
                .setGoodsId(skuVO.getGoodsIds())
                .setUpdateTime(LocalDateTime.now())
                .setCreatTime(LocalDateTime.now())
                .insert();
        redisTemplate.opsForValue().set(RedisEnum.GOODS_SKU_NUM + sku.getId(), sku.getNum());
        return true;
    }

    @Override
    public boolean delSku(Long id) {
        redisTemplate.delete(RedisEnum.GOODS_SKU_NUM + id);
        return skuMapper.deleteById(id) > 0;
    }

    @Override
    public Integer getMaxNumByActivity(Long id) {
        return skuMapper.getMaxNumByActivity(id);
    }


    /**
     * 获取路径拼接
     *
     * @param skuVO :
     * @return java.util.List<Advertise>
     * @author wangShilei
     * @date 2020/12/10 17:43
     */
    private List<SkuVO> changeUrl(List<SkuVO> skuVO) {
        String min = "?x-oss-process=image/resize,m_fill,h_50,w_50";
        skuVO.forEach(li -> li.setImgUrl(li.getImgUrl() + min));
        return skuVO;
    }
}
