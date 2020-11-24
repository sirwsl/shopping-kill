package com.wsl.shoppingKill.controller.admin;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.service.HomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页界面
 * @author WangShilei
 * @date 2020/11/24-15:06
 **/
@RestController
@RequestMapping("/admin")
public class HomeController {

    @Resource
    private HomeService homeService;

    /**
     * 前端顶部统计
     * @author wangShilei
     * @date 2020/11/24 15:11
     * @return Result<Map<String,Integer>>
     */
   public Result<Map<String,Integer>> getTotalSum(){
       Map<String,Integer> result = new HashMap<>(8);
       result.put("Number",homeService.getNumber());
       result.put("TodayNum",homeService.getTodayNum());
       result.put("TodayOrder",homeService.getTodayOrder());
       result.put("TodayOut",homeService.getTodayOut());
       return Result.success(result);
   }
}
