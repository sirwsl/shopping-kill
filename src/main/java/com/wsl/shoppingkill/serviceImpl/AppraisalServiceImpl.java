package com.wsl.shoppingkill.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingkill.common.log.MyLog;
import com.wsl.shoppingkill.obj.constant.LoggerEnum;
import com.wsl.shoppingkill.domain.Appraisal;
import com.wsl.shoppingkill.mapper.AppraisalMapper;
import com.wsl.shoppingkill.obj.vo.AppraisalVO;
import com.wsl.shoppingkill.service.AppraisalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class AppraisalServiceImpl extends ServiceImpl<AppraisalMapper, Appraisal> implements AppraisalService{

    @Resource
    private AppraisalMapper appraisalMapper;


    @Override
    public IPage<AppraisalVO> getAppraisalAll(Long current,Long size,AppraisalVO appraisalVO) {
        return appraisalMapper.getAppraisalAll(new Page<>(current,size),appraisalVO);
    }

    @Override
    @MyLog(detail = "删除评价信息",grade = LoggerEnum.SERIOUS,value = "#id")
    public boolean delAppraisalById(Long id) {
        return appraisalMapper.deleteById(id)>0;
    }

}
