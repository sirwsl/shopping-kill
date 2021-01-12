package com.wsl.shoppingkill.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.vo.OrderDetailVO;
import com.wsl.shoppingkill.obj.vo.UserOrderVO;
import com.wsl.shoppingkill.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户订单C层
 * @author : WangShiLei
 * @date : 2020/12/29 2:49 下午
 **/

@RestController
@RequestMapping("/user")
public class UserOrderInfoController {

    @Resource
    private OrderService orderService;

    /**
     * 获取用户订单概况
     *
     * @return Result<Map < String, Integer>>
     * @author wangShilei
     * @date 2020/12/29 2:52 下午
     */
    @GetMapping("/getOrderCount/v1")
    public Result<Map<String, Integer>> getOrderCount() {
        return Result.success(orderService.getOrderCount());
    }

    /**
     * 用户获取订单
     * @author wangShilei
     * @date 2020/12/30 11:15 上午
     * @param status :
     * @param name :
     * @return Result<List<UserOrderVO>>
     */
    @GetMapping("/getUserOrderInfo/v1")
    public Result<IPage<UserOrderVO>> getUserOrderInfo(Integer status, String name,
                                        @RequestParam(defaultValue = "1") Long current,@RequestParam(defaultValue = "100") Long size){
        if (status == null ){
            status =100;
        }
        return Result.success(orderService.getOrderInfo(status,name,current,size));
    }

    /**
     * 根据ID获取订单详情
     * @author wangShilei
     * @date 2020/12/30 2:11 下午
     * @param id :
     * @return Result<OrderDetailVO>
     */
    @GetMapping("/getOrderDetail/v1")
    public Result<OrderDetailVO> getOrderDetailVO(Long id){
        if(id==null){
            return Result.error("error","订单编号错误");
        }
        return Result.success( orderService.getOrderDetailVO(id));
    }

    @PutMapping("/ackGoods/v1")
    public Result<String> ackGoods(Long orderId){
        if (orderService.ackGoods(orderId)){
            return Result.success("确认收货成功");
        }else {
            return Result.error("error","确认收货失败，请稍后再试");
        }
    }
}
