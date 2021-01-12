package com.wsl.shoppingkill.serviceImpl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.oss.OssComponent;
import com.wsl.shoppingkill.domain.*;
import com.wsl.shoppingkill.mapper.ActivityMapper;
import com.wsl.shoppingkill.mapper.AppraisalMapper;
import com.wsl.shoppingkill.mapper.GoodsMapper;
import com.wsl.shoppingkill.mapper.SkuMapper;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.obj.convert.GoodsConverter;
import com.wsl.shoppingkill.obj.convert.SkuConverter;
import com.wsl.shoppingkill.obj.vo.*;
import com.wsl.shoppingkill.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
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

    @Resource
    private AppraisalMapper appraisalMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RedisTemplate<String,ViewGoodsVO> redisTemplate;


    private final String min = "?x-oss-process=image/resize,m_fill,h_50,w_50";

    private final String recomMin = "?x-oss-process=image/resize,m_fill,h_100,w_100";

    private final String viewMin = "?x-oss-process=image/resize,m_fill,h_400,w_400";

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
    public IPage<ViewGoodsVO> getViewGoodsAll(String name, Long current, Long size) {
        IPage<ViewGoodsVO> viewGoodsVOIPage = new Page<>();
        //获取商品
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>().eq(Goods.SHELF, true);
        if (StringUtils.isNotBlank(name)){
            queryWrapper.like(Goods.NAME, name);
        }
        IPage<Goods> goods = goodsMapper.selectPage(new Page<>(current, size),queryWrapper);

        //取出records
        List<ViewGoodsVO> viewGoodsVOs = GoodsConverter.INSTANCE.Goods2ViewGoodsVO(goods.getRecords());
        if (!CollectionUtils.isEmpty(viewGoodsVOs)) {
            //获取对应sku
            List<Long> ids = viewGoodsVOs.stream().map(ViewGoodsVO::getId).collect(Collectors.toList());
            Map<Long, List<Sku>> collect = skuMapper.selectList(new QueryWrapper<Sku>()
                    .in(Sku.GOODS_ID, ids)
                    .select(Sku.ID,Sku.IMG_URL, Sku.NUM, Sku.SELL_PRICE,Sku.GOODS_ID))
                    .stream()
                    .collect(Collectors.groupingBy(Sku::getGoodsId));
            //遍历goods取对应sku赋值
            viewGoodsVOs.forEach(li -> {
                List<Sku> skus = collect.get(li.getId());
                if (!CollectionUtils.isEmpty(skus)) {
                    long count = skus.stream()
                            .filter(t -> t.getNum() != null && t.getNum() > 0)
                            .map(Sku::getNum)
                            .count();
                    li.setNumber(count);
                    li.setSkuImgUrl(skus.stream()
                            .filter(t -> StringUtils.isNotBlank(t.getImgUrl()))
                            .map(t -> t.getImgUrl()+viewMin)
                            .collect(Collectors.toList()));
                    li.setSkuId(skus.stream()
                            .filter(t -> t.getId() > 0)
                            .map(Sku::getId)
                            .collect(Collectors.toList()));

                    li.setMaxPrice(Collections.max(skus, Comparator.comparing(Sku::getSellPrice)).getSellPrice());
                    li.setMinPrice(Collections.min(skus, Comparator.comparing(Sku::getSellPrice)).getSellPrice());
                    if (li.getMaxPrice().compareTo(li.getMinPrice())==0){
                        li.setMaxPrice(null);
                    }
                }
            });
        }

        return viewGoodsVOIPage.setPages(goods.getPages())
                .setTotal(goods.getTotal())
                .setRecords(viewGoodsVOs)
                .setCurrent(goods.getCurrent())
                .setSize(goods.getSize());
    }




    @Override
    @MyLog(value = "#id", detail = "商品上架处理", grade = LoggerEnum.INFO)
    public String merchandise(Long id) {

        Goods goods = goodsMapper.selectOne(new QueryWrapper<Goods>()
                .eq(Goods.ID, id)
                .select(Goods.ID,Goods.SHELF));

        Boolean shelf = goods.getShelf();
        System.err.println(shelf);
        if (shelf){
            if (goods.setShelf(false).updateById()){
                return "下架成功";
            }else{
                return "下架失败";
            }
        }else{
            if (goods.setShelf(true).updateById()){
                return "上架成功";
            }else{
                return "下架失败";
            }
        }

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

    @Override
    public List<BaseVO> getGoodsNameAll() {
        return goodsMapper.getGoodsNameAll();
    }


    @Override
    @Cached(name = "getRecommendedGoods",expire = 60,cacheType = CacheType.REMOTE,timeUnit = TimeUnit.MINUTES,localLimit = 20)
    public List<BaseGoodsVO> getRecommendedGoods(Integer size) {
        //随机获取10条记录
        String sql = "order by rand() limit "+size;
        List<Goods> goods = goodsMapper.selectList(new QueryWrapper<Goods>().eq(Goods.SHELF,true).last(sql));
        if (CollectionUtils.isEmpty(goods)){
            return new ArrayList<>();
        }
        List<BaseGoodsVO> baseGoodsVOs = GoodsConverter.INSTANCE.Goods2BaseGoodsVO(goods);
        baseGoodsVOs.forEach(li ->li.setImgUrl(li.getImgUrl()+recomMin));
        Map<Long, BigDecimal[]> priceBigAndLittle = getPriceBigAndLittle(
                baseGoodsVOs.stream().map(BaseGoodsVO::getId).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(priceBigAndLittle)){
            return new ArrayList<>();
        }

        baseGoodsVOs.forEach(li ->{
            //0-大 1-小
            BigDecimal[] bigDecimals = priceBigAndLittle.get(li.getId());
            if (bigDecimals != null){
                if (bigDecimals[0] != null && bigDecimals[0].compareTo(BigDecimal.ZERO)>0){
                    li.setMaxPrice(bigDecimals[0]);
                }
                if (bigDecimals[1] != null && bigDecimals[1].compareTo(BigDecimal.ZERO)>0){
                    li.setMinPrice(bigDecimals[1]);
                }
                if (li.getMaxPrice() != null && li.getMinPrice() != null && li.getMinPrice().compareTo(li.getMaxPrice())==0){
                    li.setMinPrice(null);
                }
            }
        });
        return baseGoodsVOs;

    }


    @Override
    public Map<Long, BigDecimal[]> getPriceBigAndLittle(List<Long> goodsIds) {
        if (CollectionUtils.isEmpty(goodsIds)){
            return null;
        }
        //获取最高最低价格
        Map<Long, BigDecimal[]> price = new LinkedHashMap<>(64);
        skuMapper.selectList(new QueryWrapper<Sku>()
                .in(Sku.GOODS_ID,goodsIds)
                .select(Sku.GOODS_ID,Sku.SELL_PRICE))
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Sku::getGoodsId))
                .forEach((k,v) ->{
                    //0-max 1-min
                    BigDecimal[] bigDecimals = new BigDecimal[2];
                    if (v.size()>=2){
                        BigDecimal sellPrice = Collections.max(v, Comparator.comparing(Sku::getSellPrice)).getSellPrice();
                        bigDecimals[0] = sellPrice;
                        sellPrice = Collections.min(v, Comparator.comparing(Sku::getSellPrice)).getSellPrice();
                        bigDecimals[1] = sellPrice;
                    }else if (v.size() == 1){
                        BigDecimal sellPrice = Collections.max(v, Comparator.comparing(Sku::getSellPrice)).getSellPrice();
                        bigDecimals[0] = sellPrice;
                    }
                    price.put(k,bigDecimals);
                });

        return price;

    }

    @Override
    @Cached(name = "goodsDetail" ,expire = 30,cacheType = CacheType.BOTH,localLimit = 50)
    public GoodsDetailVO getGoodsDetail(Long id,Integer flag) {
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();

        Goods goods = goodsMapper.selectById(id);
        if (Objects.isNull(goods)){
            return goodsDetailVO;
        }
        List<Sku> collect1 = skuMapper.selectList(new QueryWrapper<Sku>().eq(Sku.GOODS_ID, goods.getId()));
        List<Sku> skuList ;
        if (flag == 100){
            List<Long> collect  = activityMapper.selectList(new QueryWrapper<Activity>()
                    .le(Advertise.START_TIME, LocalDateTime.now())
                    .ge(Advertise.END_TIME, LocalDateTime.now()))
                    .stream()
                    .map(Activity::getSkuId)
                    .collect(Collectors.toList());
            skuList = collect1.stream().filter(li -> collect.contains(li.getId())).collect(Collectors.toList());
        }else{
            skuList = collect1;
        }

        Integer integer = appraisalMapper.selectCount(new QueryWrapper<Appraisal>().eq(Appraisal.GOODS_ID, goods.getId()));

        goodsDetailVO.setId(goods.getId()).setName(goods.getName()).setEvalNum(integer);

        List<GoodsDetailVO.Sku> skus = new ArrayList<>(8);
        skuList.forEach(li -> {
            GoodsDetailVO.Sku sku = new GoodsDetailVO.Sku();
            sku.setId(li.getId())
                    .setImgUrl(li.getImgUrl())
                    .setExpPrice(li.getExpPrice())
                    .setName(li.getAttribute())
                    .setPrice(li.getSellPrice())
                    .setTotal(li.getNum());
            skus.add(sku);
        });
        goodsDetailVO.setSkuList(skus);

        return goodsDetailVO;
    }
}
