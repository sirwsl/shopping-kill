package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.domain.AfterSales;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.domain.Sku;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.mapper.AfterSalesMapper;
import com.wsl.shoppingkill.obj.convert.AfterSalesConverter;
import com.wsl.shoppingkill.obj.convert.SkuConverter;
import com.wsl.shoppingkill.obj.param.AfterSalesParam;
import com.wsl.shoppingkill.obj.param.AfterSalesResultParam;
import com.wsl.shoppingkill.obj.vo.AfterSalesVO;
import com.wsl.shoppingkill.service.AfterSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WangShilei
 */
@Service
@Slf4j
public class AfterSalesServiceImpl extends ServiceImpl<AfterSalesMapper, AfterSales> implements AfterSalesService{

    @Resource
    private AfterSalesMapper afterSalesMapper;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;


    @Override
    public IPage<AfterSalesVO> getAfterSalesAll(Long current, Long size, AfterSalesParam afterSalesParam) {
        IPage<AfterSalesVO> afterSalesAll = afterSalesMapper.getAfterSalesAll(new Page<>(current, size), afterSalesParam);
        if (CollectionUtils.isEmpty(afterSalesAll.getRecords())){
            return afterSalesAll;
        }
        User user = new User();
        Map<Long, List<User>> userCollect = user.selectList(new QueryWrapper<User>()
                .in(User.ID, afterSalesAll.getRecords()
                        .stream()
                        .map(AfterSalesVO::getUserId)
                        .collect(Collectors.toList()))
                .select(User.ID, User.NAME, User.PHONE, User.NICK_NAME))
                .stream()
                .collect(Collectors.groupingBy(User::getId));
        Sku sku = new Sku();
        Map<Long, List<Sku>> skuCollect = sku.selectList(new QueryWrapper<Sku>()
                .in(Sku.ID, afterSalesAll.getRecords()
                        .stream()
                        .map(AfterSalesVO::getSkuId)
                        .collect(Collectors.toList()))
                .select(Sku.ID,Sku.GOODS_ID, Sku.ATTRIBUTE))
                .stream()
                .collect(Collectors.groupingBy(Sku::getId));

        ArrayList<Long> goodsId = new ArrayList<>(8);
        skuCollect.values().forEach(li -> goodsId.add(li.get(0).getGoodsId()));
        Goods goods = new Goods();
        Map<Long, List<Goods>> goodsCollect = goods.selectList(new QueryWrapper<Goods>()
                .in(Goods.ID,goodsId)
                .select(Goods.ID, Goods.NAME))
                .stream()
                .collect(Collectors.groupingBy(Goods::getId));

        afterSalesAll.getRecords().forEach(
                li ->{
                    Sku skuTemp = skuCollect.get(li.getSkuId()).get(0);
                    li.setSkuDetail(skuTemp.getAttribute())
                            .setGoodsId(skuTemp.getGoodsId())
                            .setGoodsName(goodsCollect.get(li.getGoodsId()).get(0).getName());
                    User userTemp = userCollect.get(li.getUserId()).get(0);
                    li.setUserName(userTemp.getName())
                            .setUserPhone(userTemp.getPhone())
                            .setUserNickName(userTemp.getNickName());
                }
        );

        Map<Long, List<Sku>> collect = sku.selectList(new QueryWrapper<Sku>().in(Sku.GOODS_ID,goodsId)
                .select(Sku.ID, Sku.GOODS_ID, Sku.ATTRIBUTE, Sku.NUM))
                .stream()
                .collect(Collectors.groupingBy(Sku::getGoodsId));
        afterSalesAll.getRecords().forEach(li -> li.setSkuList(SkuConverter.INSTANCE.skuAllToLittle2(collect.get(li.getGoodsId()))));
        return afterSalesAll;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @MyLog(value = "#afterSalesResultParam.id",detail = "售后处理",grade = LoggerEnum.INFO)
    public boolean refundGoodsAndMoney(AfterSalesResultParam afterSalesResultParam) throws Exception {
        if (afterSalesMapper.selectById(afterSalesResultParam.getId()).getResult()){
            return false;
        }
        boolean updateById = AfterSalesConverter.INSTANCE.afterSalesParam2DoMain(
                afterSalesResultParam.setAdminId(abstractCurrentRequestComponent.getCurrentUser().getId()))
                .setDealTime(LocalDateTime.now())
                .updateById();

        Sku sku = new Sku();
        Sku skuTemp = sku.setId(afterSalesResultParam.getOldSkuId()).selectById();
        if(BaseEnum.REFUND_GOODS_MONEY.equals(afterSalesResultParam.getType())){
            //退货退钱
           if (skuTemp.setNum(skuTemp.getNum()+1).updateById()&&updateById){
                return true;
            }
        }else if(BaseEnum.REFUND_MONEY.equals(afterSalesResultParam.getType())){
            //退钱
            return true;
        }else if(BaseEnum.REFUND_GOODS.equals(afterSalesResultParam.getType())){
            //换货
            Sku newSkuTemp = sku.setId(afterSalesResultParam.getSkuId()).selectById();
            if (newSkuTemp.setNum(newSkuTemp.getNum()-1).updateById()&&
            skuTemp.setNum(skuTemp.getNum()+1).updateById()&&updateById){
                return true;
            }
        }
        throw new Exception("售后异常，请检查售后工单与库存是否正确");

    }
}
