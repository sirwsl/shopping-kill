package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.Goods;
import com.wsl.shoppingkill.obj.vo.BaseVO;
import com.wsl.shoppingkill.obj.vo.GoodsVO;
import com.wsl.shoppingkill.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/11/19 10:19 下午
 **/
@RestController
@RequestMapping("/admin")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 添加一条商品记录
     * @author wangShilei
     * @date 2020/11/19 10:21 下午
     * @param goods :
     * @return Result<java.lang.Boolean>
     */
    @PostMapping("/addGoods/v1")
    public Result<Boolean> addGoods(@Valid Goods goods){
//        StringUtils.isBlank(advertise.getFile().getOriginalFilename())){
        if (goods.getFile() == null || StringUtils.isBlank(goods.getFile().getName())){
            return Result.error("error","上传图片不能为空");
        }
        try {
            return Result.success(goodsService.addGoods(goods));
        }catch (Exception e){
            return Result.error("error","添加商品失败，图片上传出错"+e);
        }
    }

    /**
     * 根据ID 商品名 是否上架获取一条记录
     * @author wangShilei
     * @date 2020/11/19 10:31 下午
     * @param goods :
     * @return IPage < com.wsl.shoppingkill.obj.vo.GoodsVO>
     */
    @GetMapping("getGoods/v1")
    public Result<IPage<GoodsVO>> getGoodsAll(@RequestParam(defaultValue = "1") Long current,
                                              @RequestParam(defaultValue = "10") Long size,
                                              Goods goods){
        return Result.success(goodsService.getGoodsAll(current,size,goods));
    }

    /**
     * 更新商品属性
     * @author wangShilei
     * @date 2020/11/19 10:44 下午
     * @param goods :
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     */
    @PutMapping("/updateGoods/v1")
    public Result<Boolean> updateGoods(@Valid Goods goods){
        System.err.println(goods);
        if (goods.getId()==null||goods.getId()==0){
            return Result.error("error","更新id不能为空");
        }
        if (StringUtils.isBlank(goods.getImgUrl())){
            if (goods.getFile()== null || goods.getFile().isEmpty()){
                return Result.error("error","图片不能为空");
            }

        }
        try{
            return Result.success(goodsService.updateGoods(goods));
        }catch (Exception e){
            return Result.error("error","商品更新失败"+e);
        }
    }

    /**
     * 更具ID删除一条商品记录
     * @author wangShilei
     * @date 2020/11/19 10:47 下午
     * @param id :
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     */
    @DeleteMapping("/delGoodsById/v1")
    public Result<String> delGoods(Long id){
        if (id == null || id <= 0){
            return Result.error("error","删除ID不合法");
        }
        if (!goodsService.delGoods(id)){
            return Result.error("error","Sku数量大于0，不能被删除");
        }
        return Result.success("删除成功");
    }

    /**
     * 商品上架或下架
     * @author wangShilei
     * @date 2020/11/19 10:48 下午
     * @param id :
     * @return com.wsl.shoppingkill.common.Result<String>
     */
    @PutMapping("/merchandise/v1")
    public Result<String> merchandise(Long id){
        if (id == null || id <= 0){
            return Result.error("error","删除ID不合法");
        }
        return Result.success(goodsService.merchandise(id));
    }

    /**
     * 获取所有物品
     * <p>
     *     Object -> (id,name)
     * </p>
     * @author wangShilei
     * @date 2020/12/16 9:55
     * @return com.wsl.shoppingkill.common.Result<java.util.List<java.lang.Object>>
     */
    @GetMapping("/getAllGoodsName/v1")
    public Result<List<BaseVO>> getGoodsNameAll(){
        return Result.success(goodsService.getGoodsNameAll());
    }
}
