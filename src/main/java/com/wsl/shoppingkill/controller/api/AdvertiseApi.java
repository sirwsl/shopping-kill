package com.wsl.shoppingkill.controller.api;

import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.vo.AdvertiseVO;
import com.wsl.shoppingkill.service.AdvertiseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/11/9 11:16 下午
 **/
@RestController
@RequestMapping("/api")
public class AdvertiseApi {

    @Resource
    private AdvertiseService advertiseService;

    /**
     * 获取展示广告
     *
     * @return Result<List < AdvertiseVO>>
     * @author : WangShiLei
     * @date : 2020/11/9 11:25 下午
     **/
    @GetMapping("/getAdvertiseForView/v1")
    public Result<List<AdvertiseVO>> getAdvertiseVO() {
        return Result.success(advertiseService.getAdvertiseUrl(0));
    }

}
