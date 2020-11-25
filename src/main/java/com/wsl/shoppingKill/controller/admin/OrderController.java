package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.obj.param.OrderParam;
import com.wsl.shoppingKill.obj.vo.OrderVO;
import com.wsl.shoppingKill.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Field;
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
     * @author wangShilei
     * @date 2020/11/25 14:34
     * @param current :
     * @param size :
     * @param orderParam :
     * @return Result<IPage<OrderVO>>
     */
    @GetMapping("/getOrderAllById/v1")
    public Result<IPage<OrderVO>> getOrderAllByType(@RequestParam(defaultValue = "1")Long current, @RequestParam(defaultValue = "10") Long size,
                                                    OrderParam orderParam){
        if(Objects.isNull(orderParam)){
            return Result.error("error","请求参数不能为空");
        }

        if (orderParam.getType() != null&&(orderParam.getType()< BaseEnum.ORDER_TYPE_BEGIN||orderParam.getType()>BaseEnum.ORDER_TYPE_END)){
            return Result.error("error","订单类型错误");
        }

        for (Field temp:orderParam.getClass().getDeclaredFields()){
            temp.setAccessible(true);
            try {
                if (temp.get(orderParam) != null && StringUtils.isNotEmpty(temp.get(orderParam).toString())){
                    return Result.success(orderService.getAllOrder(orderParam,current,size));
                }
            } catch (IllegalAccessException e) {
                log.info("前端参数异常");
                return Result.error("error","缺少必要参数");
            }
        }
        return Result.error("error","缺少必要参数");
    }
}
