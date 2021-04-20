package com.wsl.shoppingkill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.Types;
import com.wsl.shoppingkill.service.TypesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 商品类别API
 *
 * @author WangShilei
 * @date 2020/11/18-15:54
 **/

@RestController
@RequestMapping("/admin")
public class TypesController {

    @Resource
    private TypesService typesService;

    /**
     * 添加商品类别
     *
     * @param types :
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/18 16:46
     */
    @PostMapping("/addTypes/v1")
    public Result<Boolean> addTypes(@Valid Types types) {
        return Result.success(typesService.addTypes(types));
    }

    /**
     * 更具id删除Type
     *
     * @param id :
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/18 16:49
     */
    @DeleteMapping("/delTypes/v1")
    public Result<Boolean> delTypes(Integer id) {
        if (id == null || id <= 0) {
            return Result.error("error", "id不能为空也不能为负数");
        }
        return Result.success(typesService.delTypes(id));
    }

    /**
     * 更新商品类别
     *
     * @param types :
     * @return com.wsl.shoppingkill.common.Result<java.lang.Boolean>
     * @author wangShilei
     * @date 2020/11/18 17:12
     */
    @PutMapping("/updateTypes/v1")
    public Result<Boolean> updateTypes(@Valid Types types) {
        return Result.success(typesService.updateTypes(types));
    }

    /**
     * 获取全部分类列表
     *
     * @param current :
     * @param size    :
     * @return IPage<com.wsl.shoppingkill.domain.Types>>
     * @author wangShilei
     * @date 2020/11/18 16:52
     */
    @GetMapping("/getTypesAll/v1")
    public Result<IPage<Types>> getTypesAll(@RequestParam(defaultValue = "1") Long current, @RequestParam(defaultValue = "10") Long size) {
        return Result.success(typesService.getTypesAll(current, size));
    }


    /**
     * 更具name模糊匹配
     *
     * @param current :
     * @param size    :
     * @param name    :
     * @return IPage<Types>>
     * @author wangShilei
     * @date 2020/11/18 17:03
     */
    @GetMapping("/getTypesByNameAndId/v1")
    public Result<IPage<Types>> getTypesByName(@RequestParam(defaultValue = "1") Long current, @RequestParam(defaultValue = "10") Long size, String name, Integer id) {
        return Result.success(typesService.getTypesByNameAndId(current, size, name, id));
    }

}
