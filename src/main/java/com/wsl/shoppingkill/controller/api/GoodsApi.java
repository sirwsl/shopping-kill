package com.wsl.shoppingkill.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.vo.BaseGoodsVO;
import com.wsl.shoppingkill.obj.vo.GoodsDetailVO;
import com.wsl.shoppingkill.obj.vo.ViewGoodsVO;
import com.wsl.shoppingkill.service.GoodsService;
import com.wsl.shoppingkill.service.adapter.GoodsAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WangShilei
 * @date 2020/12/22-16:34
 **/
@RestController
@RequestMapping("/api")
public class GoodsApi {

    @Resource
    private GoodsService goodsService;

    @Resource
    private GoodsAdapter goodsAdapter;

    /**
     * 获取推荐列表（随机推荐）
     * @author wangShilei
     * @date 2020/12/22 16:47
     * @param size : 
     * @return Result<BaseGoodsVO>>
     */
    @GetMapping("/getRecommendedGoods/v1")
    public Result< List<BaseGoodsVO>> recommendedGoods(@RequestParam(defaultValue = "5") Integer size){
        if (size == null || size <0){
            return Result.error("error","参数不正确");
        }
        return Result.success(goodsService.getRecommendedGoods(size));
    }


    /**
     * 获取商品展示
     * @author wangShilei
     * @date 2020/12/23 10:54
     * @return Result<List<ViewGoodsVO>>
     */
    @GetMapping("/getAllGoods/v1")
    public Result<IPage<ViewGoodsVO>> recommendedGoods(String name,@RequestParam(defaultValue = "1") Long current){
        if (StringUtils.isNotBlank(name)){
            return Result.success(goodsAdapter.getViewGoodsAll(name,current,null));
        }
        return Result.success(goodsAdapter.getViewGoods(current,null));
    }

    /**
     * 根据ID获取商品信息
     * @author wangShilei
     * @date 2020/12/28 3:20 下午
     * @param id :
     * @return com.wsl.shoppingkill.common.Result<com.wsl.shoppingkill.obj.vo.GoodsDetailVO>
     */
    @GetMapping("/getDetail/v1")
    public Result<GoodsDetailVO> goodsDetailVO(Long id,Integer flag){
        if (id ==null || id ==0){
            return Result.error("error","物品ID不能为空");
        }
        if (flag == null){
            return Result.error("error","参数不正确");
        }
        if (!(flag == 100 || flag == 1 || flag == 0)){
            return Result.error("error","参数不正确");
        }
        return Result.success(goodsService.getGoodsDetail(id,flag));
    }

}
