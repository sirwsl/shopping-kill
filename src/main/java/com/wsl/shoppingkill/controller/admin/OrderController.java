package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.constant.BaseEnum;
import com.wsl.shoppingkill.obj.param.OrderParam;
import com.wsl.shoppingkill.obj.vo.OrderVO;
import com.wsl.shoppingkill.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author WangShilei
 * @date 2020/11/25-14:13
 **/

@RestController
@RequestMapping("/admin")
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 根据订单类型获取订单
     *
     * @param current    :
     * @param size       :
     * @param orderParam :
     * @return Result<IPage < OrderVO>>
     * @author wangShilei
     * @date 2020/11/25 14:34
     */
    @GetMapping("/getOrderAllById/v1")
    public Result<IPage<OrderVO>> getOrderAllByType(@RequestParam(defaultValue = "1") Long current, @RequestParam(defaultValue = "10") Long size,
                                                    OrderParam orderParam) {
        if (Objects.isNull(orderParam)) {
            return Result.error("error", "请求参数不能为空");
        }

        if (orderParam.getType() != null && (orderParam.getType() < BaseEnum.ORDER_TYPE_BEGIN || orderParam.getType() > BaseEnum.ORDER_TYPE_END)) {
            return Result.error("error", "订单类型错误");
        }

        for (Field temp : orderParam.getClass().getDeclaredFields()) {
            temp.setAccessible(true);
            try {
                if (temp.get(orderParam) != null && StringUtils.isNotEmpty(temp.get(orderParam).toString())) {
                    return Result.success(orderService.getAllOrder(orderParam, current, size));
                }
            } catch (IllegalAccessException e) {
                log.info("前端参数异常");
                return Result.error("error", "缺少必要参数");
            }
        }
        return Result.error("error", "缺少必要参数");
    }

    /**
     * 提醒支付
     *
     * @param orderId :
     * @return Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/25 18:45
     */
    @GetMapping("/remind2Pay/v1")
    public Result<Boolean> remind2Pay(Long orderId) {
        if (orderId == null || orderId == 0) {
            return Result.error("error", "订单id不能为空");
        }
        return Result.success(orderService.remind2Pay(orderId));
    }

    /**
     * 修改价格
     *
     * @param orderId :
     * @return Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/25 18:47
     */
    @GetMapping("/modifyPrice/v1")
    public Result<Boolean> modifyPrice(Long orderId, BigDecimal bigDecimal) {
        if (orderId == null || orderId == 0) {
            return Result.error("error", "订单id不能为空");
        }
        if (bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.error("error", "价格不能为空，更不能小于0");
        }
        return Result.success(orderService.modifyPrice(orderId, bigDecimal));
    }

    /**
     * 出库短信通知
     *
     * @param orderId :
     * @return Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/25 18:46
     */
    @GetMapping("/outGoods/v1")
    public Result<Boolean> outGoods(Long orderId) {
        if (orderId == null || orderId == 0) {
            return Result.error("error", "订单id不能为空");
        }
        return Result.success(orderService.outGoods(orderId));
    }

    /**
     * 提醒评价
     *
     * @param orderId :
     * @return Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/25 18:46
     */
    @GetMapping("/reminderEvaluation/v1")
    public Result<Boolean> reminderEvaluation(Long orderId) {
        if (orderId == null || orderId == 0) {
            return Result.error("error", "订单id不能为空");
        }
        return Result.success(orderService.reminderEvaluation(orderId));
    }
}
