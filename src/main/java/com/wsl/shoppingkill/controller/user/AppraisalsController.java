package com.wsl.shoppingkill.controller.user;


import com.wsl.shoppingkill.common.Result;


import com.wsl.shoppingkill.domain.Appraisal;
import com.wsl.shoppingkill.obj.vo.AppraisalUserVO;

import com.wsl.shoppingkill.service.AppraisalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.util.List;

/**
 * 用户评价
 *
 * @author : WangShiLei
 * @date : 2020/12/29 3:45 下午
 **/

@RestController
@RequestMapping("/user")
public class AppraisalsController {

    @Resource
    private AppraisalService appraisalService;

    /**
     * 获取当前用户的商品评价
     *
     * @param flag :
     * @return Result<List < AppraisalUserVO>>
     * @author wangShilei
     * @date 2020/12/29 5:31 下午
     */
    @GetMapping("/getAppraisalAll/v1")
    public Result<List<AppraisalUserVO>> getAppraisalAll(Boolean flag) {
        if (flag == null) {
            return Result.error("error", "获取评价参数错误");
        }
        return Result.success(appraisalService.getAppraisalAll(flag));
    }

    /**
     * 用户添加评价
     *
     * @param appraisal :
     * @return Result<java.lang.String>
     * @author wangShilei
     * @date 2020/12/29 9:00 下午
     */
    @PostMapping("/addAppraisal/v1")
    public Result<String> addAppraisal(@Valid Appraisal appraisal) {
        if (appraisalService.addAppraisal(appraisal)) {
            return Result.success("您的评价已被收录，感谢您的评价");
        }
        return Result.error("error", "评价失败，请您稍后再试");
    }

    /**
     * 删除评价信息
     *
     * @param id :
     * @return com.wsl.shoppingkill.common.Result<java.lang.String>
     * @author wangShilei
     * @date 2020/12/29 9:04 下午
     */
    @DeleteMapping("/deleteAppraisal/v1")
    public Result<String> delAppraisal(Long id) {
        if (appraisalService.delAppraisalById(id)) {
            return Result.success("您的评价已删除");
        }
        return Result.error("error", "删除失败，请您稍后再试");
    }

}
