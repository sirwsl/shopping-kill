package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.Loggers;
import com.wsl.shoppingkill.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/getTotalToday/v1")
   public Result<Map<String,Integer>> getTotalSum(){
       Map<String,Integer> result = new HashMap<>(8);
       result.put("Number",homeService.getNumber());
       result.put("TodayNum",homeService.getTodayNum());
       result.put("TodayOrder",homeService.getTodayOrder());
       result.put("TodayOut",homeService.getTodayOut());
       return Result.success(result);
   }




    /**
     * 获取操作日志
     * @author wangShilei
     * @date 2020/11/24 16:46
     * @return com.wsl.shoppingkill.common.Result<com.wsl.shoppingkill.domain.Loggers>
     */
    @GetMapping("/getLoggersAll/v1")
    public Result<Map<String, IPage<Loggers>>> getLoggersAll(@RequestParam(defaultValue = "1") Long current1,@RequestParam(defaultValue = "15")Long size1,
                                                             @RequestParam(defaultValue = "1") Long current2,@RequestParam(defaultValue = "16")Long size2){
        return Result.success(homeService.getLoggersAll(current1,size1,current2,size2));
    }
}
