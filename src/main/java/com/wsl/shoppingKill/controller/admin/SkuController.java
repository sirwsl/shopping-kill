package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.obj.vo.SkuVO;
import com.wsl.shoppingKill.service.SkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO:
 * SKU界面管理
 * @author WangShilei
 * @date 2020/11/17-17:49
 **/
@RestController
@RequestMapping("/admin")
public class SkuController {

    @Resource
    private SkuService skuService;

    @GetMapping("/getSkuVoById/v1")
    public Result<SkuVO> getSkuVoById(Long id){
        return Result.success(skuService.getSku(id));
    }

    @GetMapping("/getSkuAll/v1")
    public Result<IPage<SkuVO>> getSkuAll(@RequestParam(defaultValue = "1") Long current,@RequestParam(defaultValue = "10")Long size,
                                          Long id, String name){
        return Result.success(skuService.getSkuAll(current,size,id,name));
    }
}
