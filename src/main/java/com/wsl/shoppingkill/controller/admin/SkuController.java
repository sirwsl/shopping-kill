package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.obj.vo.SkuVO;
import com.wsl.shoppingkill.obj.vo.SkuVOs;
import com.wsl.shoppingkill.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * TODO:
 * SKU界面管理
 *
 * @author WangShilei
 * @date 2020/11/17-17:49
 **/
@RestController
@RequestMapping("/admin")
@Slf4j
public class SkuController {

    @Resource
    private SkuService skuService;

    @GetMapping("/getSkuVoById/v1")
    public Result<SkuVO> getSkuVoById(Long id) {
        if (id == null || id == 0) {
            return Result.error("error", "id不能为空");
        }
        return Result.success(skuService.getSku(id));
    }

    @GetMapping("/getSkuAll/v1")
    public Result<IPage<SkuVO>> getSkuAll(@RequestParam(defaultValue = "1") Long current, @RequestParam(defaultValue = "10") Long size,
                                          Long id, String name) {
        return Result.success(skuService.getSkuAll(current, size, id, name));
    }

    @PostMapping("/updateSkuInfo/v1")
    public Result<Boolean> updateSkuInfo(@Valid SkuVO skuVO) {
        if (StringUtils.isBlank(skuVO.getImgUrl()) && skuVO.getImg().getName().isEmpty()) {
            return Result.error("error", "图片不能为空");
        }
        try {
            return Result.success(skuService.updateSku(skuVO));
        } catch (Exception e) {
            log.error("SKU图片上传出错或数据库服务器异常");
            return Result.error("error", "文件上传出错或服务器异常");
        }
    }

    @PostMapping("/addSkuInfo/v1")
    public Result<Boolean> addSkuInfo(@Valid SkuVOs skuVO) {
        if (skuVO.getImgs() == null || skuVO.getImgs().getName().isEmpty()) {
            return Result.error("error", "图片不能为空");
        }
        try {
            return Result.success(skuService.addSku(skuVO));
        } catch (Exception e) {
            log.error("SKU图片上传出错或数据库服务器异常");
            return Result.error("error", "文件上传出错或服务器异常");
        }
    }

    @DeleteMapping("/delSkuById/v1")
    public Result<Boolean> delSkuInfo(Long id) {
        if (id == null || id == 0) {
            return Result.error("error", "删除SKU的id不能为空");
        }
        return Result.success(skuService.delSku(id));
    }
}
