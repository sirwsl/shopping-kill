package com.wsl.shoppingkill.controller.user;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.common.util.IpUtils;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.User;
import com.wsl.shoppingkill.obj.constant.RedisEnum;
import com.wsl.shoppingkill.obj.param.AddOrderParam;
import com.wsl.shoppingkill.service.AddOrderService;
import com.wsl.shoppingkill.service.GetUrlService;
import com.wsl.shoppingkill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 下单接口
 * @author : WangShiLei
 * @date : 2021/1/1 5:15 下午
 **/

@RestController
@RequestMapping("/user")
public class AddOrderController {

    @Resource
    private AddOrderService addOrderService;

    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private GetUrlService getUrlService;

    @PostMapping("/commonGoods/v1")
    public Result<String> commonGoods(AddOrderParam addOrderParam, HttpServletRequest request){
        if (checkBlackList(request)) {
            return Result.error("error","由于您在黑名单中下单,下单失败");
        }
        addOrderParam.setUserId(abstractCurrentRequestComponent.getCurrentUser().getId());
        String s = addOrderService.commonOrder(addOrderParam);
        if (StringUtils.isNotBlank(s)) {
            return Result.success(s);
        }
        return Result.error("error","下单失败");
    }

    @PostMapping("/killGoods/{goodsId}/{num}/{md5}")
    public Result<String> killGoods(@PathVariable("goodsId")Long goodsId,
                                    @PathVariable("num")Integer num,
                                    @PathVariable("md5")String md5,
                                    HttpServletRequest request){
        Long id = abstractCurrentRequestComponent.getCurrentUser().getId();
        if (StringUtils.isBlank(md5)){
            return Result.error("error","找不到接口位置");
        }
        if (!md5.equals(getUrlService.getMd5(goodsId))) {
            return Result.error("error","秒杀地址不正确");
        }
        Object o = redisTemplate.opsForValue().get(RedisEnum.USER_KILL_NUM + id + goodsId);
        if (Objects.nonNull(o)){
            return Result.error("error","您已经抢购过该商品了");
        }
        if(num < 1){
            return Result.error("error","数量不正确");
        }
        if (checkBlackList(request)) {
            return Result.error("error","由于您在黑名单中下单,下单失败");
        }

        AddOrderParam addOrderParam = new AddOrderParam(goodsId,num,id);
        String s = addOrderService.killOrder(addOrderParam);
        if (StringUtils.isNotBlank(s)) {
            return Result.success(s);
        }
        return Result.error("error","下单失败");
    }

    private boolean checkBlackList(HttpServletRequest request){
        Long userId = abstractCurrentRequestComponent.getCurrentUser().getId();
        User user = userService.getById(userId);
        Object phone = redisTemplate.opsForValue().get(RedisEnum.LIMIT_LIST + user.getPhone());
        if (Objects.nonNull(phone)){
            return true;
        }
        String ip = IpUtils.getIP(request);
        Object ips = redisTemplate.opsForValue().get(RedisEnum.LIMIT_LIST + ip);
        return Objects.nonNull(ips);
    }
}
