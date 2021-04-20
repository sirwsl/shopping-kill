package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.Advertise;
import com.wsl.shoppingkill.service.AdvertiseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : WangShiLei
 * @date : 2020/11/9 11:16 下午
 **/
@RestController
@RequestMapping("/admin")
public class AdvertiseController {

    @Resource
    private AdvertiseService advertiseService;


    /**
     * 添加一条广告
     *
     * @param advertise:
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     * @author : WangShiLei
     * @date : 2020/11/9 11:20 下午
     **/
    @PostMapping("/addAdvertise/v1")
    public Result<String> addAdvertise(@Valid Advertise advertise) {
        if (StringUtils.isBlank(advertise.getFile().getOriginalFilename())) {
            return Result.error("error", "上传文件不能为空");
        }
        return Result.success(advertiseService.addAdvertise(advertise));
    }

    /**
     * 更新一条广告
     *
     * @param advertise:
     * @return Result<java.lang.Boolean>
     * @author : WangShiLei
     * @date : 2020/11/9 11:22 下午
     **/
    @PostMapping("/updateAdvertise/v1")
    public Result<String> updateAdvertise(@Valid Advertise advertise) {
        if (advertise.getId() == null || advertise.getId() == 0) {
            return Result.error("error", "id不能为空");
        }
        return Result.success(advertiseService.updateAdvertise(advertise));
    }

    /**
     * 删除一条广告
     *
     * @param id:
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     * @author : WangShiLei
     * @date : 2020/11/9 11:24 下午
     **/
    @DeleteMapping("/delAdvertise/v1")
    public Result<Boolean> delAdvertise(Long id) {
        if (id == null || id == 0) {
            return Result.error("error", "删除id不能为空");
        }
        return Result.success(advertiseService.delAdvertise(id));
    }


    /**
     * 获取全部广告
     *
     * @param page:
     * @param size  :
     * @return Result<IPage < Advertise>>
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     **/
    @GetMapping("/getAdvertiseAll/v1")
    public Result<IPage<Advertise>> getAdvertiseAll(@RequestParam(defaultValue = "0") Long page,
                                                    @RequestParam(defaultValue = "10") Long size) {
        return Result.success(advertiseService.getAdvertisePage(page, size));
    }

    /**
     * 获取正在进行中的广告
     *
     * @param page:
     * @param size  :
     * @return Result<IPage < Advertise>>
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     **/
    @GetMapping("/getAdvertiseDoing/v1")
    public Result<IPage<Advertise>> getAdvertiseDoing(@RequestParam(defaultValue = "0") Long page,
                                                      @RequestParam(defaultValue = "10") Long size) {
        return Result.success(advertiseService.getAdvertiseDoing(page, size));
    }

    /**
     * 获取已经结束的广告
     *
     * @param page:
     * @param size  :
     * @return Result<IPage < Advertise>>
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     **/
    @GetMapping("/getAdvertiseOver/v1")
    public Result<IPage<Advertise>> getAdvertiseOver(@RequestParam(defaultValue = "0") Long page,
                                                     @RequestParam(defaultValue = "10") Long size) {
        return Result.success(advertiseService.getAdvertiseOver(page, size));
    }

    /**
     * 获取还未开始爹广告
     *
     * @param page:
     * @param size  :
     * @return Result<IPage < Advertise>>
     * @author : WangShiLei
     * @date : 2020/11/9 11:30 下午
     **/
    @GetMapping("/getAdvertiseBegin/v1")
    public Result<IPage<Advertise>> getAdvertiseBegin(@RequestParam(defaultValue = "0") Long page,
                                                      @RequestParam(defaultValue = "10") Long size) {
        return Result.success(advertiseService.getAdvertiseBegin(page, size));
    }
}
