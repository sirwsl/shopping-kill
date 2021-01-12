package com.wsl.shoppingkill.controller.user;

import com.baomidou.mybatisplus.extension.api.R;
import com.wsl.shoppingkill.common.Result;
import com.wsl.shoppingkill.domain.Address;
import com.wsl.shoppingkill.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author : WangShiLei
 * @date : 2020/12/29 12:59 下午
 **/
@RestController
@RequestMapping("/user")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 获取当前收件人所有地址
     * @author wangShilei
     * @date 2020/12/29 1:08 下午
     * @return Result<Address>
     */
    @GetMapping("/getAddress/v1")
    public Result<List<Address>> getAddressByUser(){
        return Result.success(addressService.getAddressByUser());
    }

    /**
     * 更新或修改收件人地址
     * @author wangShilei
     * @date 2020/12/29 1:17 下午
     * @param address :
     * @return Result<java.lang.String>
     */
    @PostMapping("/addOrUpdateAddress/v1")
    public Result<String> addOrUpdateAddress(@Valid Address address){
        if (addressService.addOrUpdateAddress(address)){
            return Result.success("操作成功");
        }
        return Result.error("error","操作失败");
    }
    /**
     * 根据id删除收件人地址
     * @author wangShilei
     * @date 2020/12/29 8:33 下午
     * @param id :
     * @return Result<String>
     */
    @DeleteMapping("/delAddressById/v1")
    public Result<String> delAddress(Long id){
        if (id == null || id <= 0){
            return Result.error("error","操作失败，参数不对");
        }
        if (addressService.delAddress(id)){
            return Result.success("删除收件人成功");
        }
        return Result.error("error","删除收件人失败，请稍后再试");
    }
    /**
     * 设置默认收件地址
     * @author wangShilei
     * @date 2020/12/30 3:36 下午
     * @param id :
     * @return Result<String>
     */
    @PutMapping("/setDefaultAddress/v1")
    public Result<String> setDefaultAddress(Long id){
        if (id == null || id <= 0){
            return Result.error("error","设置失败，参数不对");
        }
        if (addressService.updateDefaultAddress(id)){
            return Result.success("设置默认地址成功");
        }
        return Result.error("error","设置失败，请稍后再试");
    }
}
