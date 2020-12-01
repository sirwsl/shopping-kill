package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Appraisal;
import com.wsl.shoppingkill.obj.vo.AppraisalVO;

/**
 * @author wangShilei
 */
public interface AppraisalService extends IService<Appraisal> {

    /**
     * 获取全部的评价内容
     * @param current :
     * @param appraisalVO :
     * @param size :
     * @return IPage<AppraisalVO>
     * @author wangshilei
     * @date 2020/11/18 11:01
     **/
    IPage<AppraisalVO> getAppraisalAll(Long current,Long size, AppraisalVO appraisalVO);

    /**
     * 根据id删除评价
     * @param id :
     * @return boolean
     * @author wangshilei
     * @date 2020/11/18 15:06
     **/
    boolean delAppraisalById(Long id);
}
