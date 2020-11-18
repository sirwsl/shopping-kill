package com.wsl.shoppingKill.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.obj.vo.AppraisalVO;
import com.wsl.shoppingKill.service.AppraisalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author WangShilei
 * @date 2020/11/18-10:49
 **/
@RestController
@RequestMapping("/admin")
public class AppraisalController {

    @Resource
    private AppraisalService appraisalService;

    /**
     * 获取所有的评价内容
     * @param current :
     * @param size :
     * @param appraisalVO :
     * @return Result<IPage<AppraisalVO>>
     * @author wangshilei
     * @date 2020/11/18 11:04
     **/
    @GetMapping("/getAppraisalAll/v1")
    public Result<IPage<AppraisalVO>> getAppraisalAll(@RequestParam(defaultValue = "1") Long current, @RequestParam(defaultValue = "10")Long size, AppraisalVO appraisalVO){
        return Result.success(appraisalService.getAppraisalAll(current,size,appraisalVO));
    }

    /**
     *
     * @param id :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/18 15:04
     **/
    @DeleteMapping("delAppraisalById/v1")
    public Result<Boolean> delAppraisalById(Long id){
        if(id == null || id == 0){
            return Result.error("error","id不能为空");
        }
        return Result.success(appraisalService.delAppraisalById(id));
    }
}
