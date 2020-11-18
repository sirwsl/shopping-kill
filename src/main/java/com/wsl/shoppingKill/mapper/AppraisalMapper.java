package com.wsl.shoppingKill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsl.shoppingKill.domain.Appraisal;
import com.wsl.shoppingKill.obj.vo.AppraisalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangShilei
 */
@Mapper
public interface AppraisalMapper extends BaseMapper<Appraisal> {

    /**
     * 获取全部的评价内容
     * @param page :
     * @param appraisalVO :
     * @return IPage<AppraisalVO>
     * @author wangshilei
     * @date 2020/11/18 10:59
     **/
    IPage<AppraisalVO> getAppraisalAll(Page<AppraisalVO> page,@Param("appraisal") AppraisalVO appraisalVO);

}