package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.component.oss.OssComponent;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.domain.Advertise;
import com.wsl.shoppingKill.obj.vo.AdvertiseVO;
import com.wsl.shoppingKill.service.admin.AdvertiseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/11/9 11:16 下午
 **/
@RestController
@RequestMapping("/admin")
public class AdvertiseController {

    @Resource
    private AdvertiseService advertiseService;

    @Resource
    OssComponent ossComponent;
    @PostMapping("/upload")
    public Result<String> addAdvertise(MultipartFile file){
        String s = ossComponent.uploadFile(BaseEnum.OSS_OTHER, file);
        return Result.success(s);
    }

    /**
     * 添加一条广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:20 下午
     * @param advertise:
     * @return com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     **/
    @PostMapping("/addAdvertise/v1")
    public Result<Boolean> addAdvertise(@Valid Advertise advertise){
        return Result.success(advertiseService.addAdvertise(advertise));
    }

    /**
     * 更新一条广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:22 下午
     * @param advertise:
     * @return com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     **/
    @PutMapping("/updateAdvertise/v1")
    public Result<Boolean> updateAdvertise(@Valid Advertise advertise){
        return Result.success(advertiseService.updateAdvertise(advertise));
    }

    /**
     * 删除一条广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:24 下午
     * @param id:
     * @return com.wsl.shoppingKill.common.Result<java.lang.Boolean>
     **/
    @DeleteMapping("/delAdvertise/v1")
    public Result<Boolean> delAdvertise(@NotNull(message = "id不能为空") Long id){
        return Result.success(advertiseService.delAdvertise(id));
    }

    /**
     *  获取展示广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:25 下午
     * @return Result<List<AdvertiseVO>>
     **/
    @GetMapping("/getAdvertiseForView/v1")
    public Result<List<AdvertiseVO>> getAdvertiseVO(){
        return Result.success(advertiseService.getAdvertiseUrl());
    }

    /**
     * 获取全部广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     * @param page:
     * @param size :
     * @return Result<IPage<Advertise>>
     **/
    @GetMapping("/getAdvertiseAll/v1")
    public Result<IPage<Advertise>> getAdvertiseAll(@RequestParam(defaultValue = "0") Long page,
                                                    @RequestParam(defaultValue = "10") Long size){
        return Result.success(advertiseService.getAdvertisePage(page,size));
    }

    /**
     * 获取正在进行中的广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     * @param page:
     * @param size :
     * @return Result<IPage<Advertise>>
     **/
    @GetMapping("/getAdvertiseDoing/v1")
    public Result<IPage<Advertise>> getAdvertiseDoing(@RequestParam(defaultValue = "0") Long page,
                                                    @RequestParam(defaultValue = "10") Long size){
        return Result.success(advertiseService.getAdvertiseDoing(page,size));
    }

    /**
     * 获取已经结束的广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     * @param page:
     * @param size :
     * @return Result<IPage<Advertise>>
     **/
    @GetMapping("/getAdvertiseOver/v1")
    public Result<IPage<Advertise>> getAdvertiseOver(@RequestParam(defaultValue = "0") Long page,
                                                    @RequestParam(defaultValue = "10") Long size){
        return Result.success(advertiseService.getAdvertiseOver(page,size));
    }

    /**
     * 获取还未开始爹广告
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     * @param page:
     * @param size :
     * @return Result<IPage<Advertise>>
     **/
    @GetMapping("/getAdvertiseBegin/v1")
    public Result<IPage<Advertise>> getAdvertiseBegin(@RequestParam(defaultValue = "0") Long page,
                                                    @RequestParam(defaultValue = "10") Long size){
        return Result.success(advertiseService.getAdvertiseBegin(page,size));
    }
}
