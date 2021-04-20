package com.wsl.shoppingkill.controller.user;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.vo.CartVO;
import com.wsl.shoppingkill.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/12/28 4:13 下午
 **/
@RestController
@RequestMapping("/user")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param skuId :
     * @return com.wsl.shoppingkill.common.Result<java.lang.String>
     * @author wangShilei
     * @date 2020/12/28 4:25 下午
     */
    @PostMapping("/addCart/v1")
    public Result<String> addCart(Long skuId, Integer num) {
        if (skuId == null || skuId <= 0) {
            return Result.error("error", "添加商品不合法");
        }
        if (cartService.addCart(skuId, num)) {
            return Result.success("添加收藏夹成功");
        }
        return Result.error("error", "添加失败请稍后重试");
    }

    /**
     * 用户获取购物车列表
     *
     * @return Result<java.util.List < com.wsl.shoppingkill.obj.vo.CartVO>>
     * @author wangShilei
     * @date 2020/12/28 5:05 下午
     */
    @GetMapping("/getCart/v1")
    public Result<List<CartVO>> getCart() {
        return Result.success(cartService.getCartAll());
    }

    /**
     * 用户删除购物车
     *
     * @param ids :
     * @return Result<java.lang.String>
     * @author wangShilei
     * @date 2020/12/28 5:05 下午
     */
    @DeleteMapping("/delCartByIds/v1")
    public Result<String> delCart(Long ids) {
        Integer temp = cartService.delCartByIds(ids);
        if (temp != null && temp > 0) {
            return Result.success("收藏夹商品删除成功");
        }
        return Result.error("error", "商品商品失败");
    }

}
