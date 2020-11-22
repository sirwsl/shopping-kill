package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.constant.BaseEnum;
import com.wsl.shoppingKill.obj.param.AfterSalesParam;
import com.wsl.shoppingKill.obj.param.AfterSalesResultParam;
import com.wsl.shoppingKill.obj.vo.AfterSalesVO;
import com.wsl.shoppingKill.service.AfterSalesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 售后服务
 * @author : WangShiLei
 * @date : 2020/11/22 10:27 下午
 **/
@RestController
@RequestMapping("/admin")
public class AfterSalesController {

    @Resource
    private AfterSalesService afterSalesService;

    /**
     * 获取售后列表
     * @author wangShilei
     * @date 2020/11/22 10:34 下午
     * @param current :
     * @param size :
     * @param afterSalesParam :
     * @return IPage < com.wsl.shoppingKill.obj.vo.AfterSalesVO>>
     */
    @GetMapping("/getAfterSalesAll/v1")
    public Result<IPage<AfterSalesVO>> getAfterSalesAll(@RequestParam(defaultValue = "1")Long current, @RequestParam(defaultValue = "10") Long size
                                                 , AfterSalesParam afterSalesParam){
       return Result.success(afterSalesService.getAfterSalesAll(current, size, afterSalesParam));
    }
    /**
     * 售后问题处理
     * @author wangShilei
     * @date 2020/11/22 10:41 下午
     * @param afterSalesResultParam :
     * @return com.wsl.shoppingKill.common.Result<java.lang.String>
     */
    @PutMapping("/refundGoodsAndMoney/v1")
    public Result<String> refundGoodsAndMoney(@Valid AfterSalesResultParam afterSalesResultParam){

        if(BaseEnum.REFUND_GOODS.equals(afterSalesResultParam.getType())){
            if(afterSalesResultParam.getSkuId()==null || afterSalesResultParam.getSkuId() == 0){
                return Result.error("error","换货SkuID不能为空");
            }
        }
        try {
            if (afterSalesService.refundGoodsAndMoney(afterSalesResultParam)){
                return Result.success("售后问题处理成功");
            }
            return Result.error("error","售后工单处理失败,请重新查看是否已经处理或内容是否符合规范");
        }catch (Exception e){
            return Result.error("error","处理失败");
        }
    }


}
