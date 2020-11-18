package com.wsl.shoppingKill.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsl.shoppingKill.domain.Appraisal;
import com.wsl.shoppingKill.mapper.AppraisalMapper;
import com.wsl.shoppingKill.service.AppraisalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author WangShilei
 */
@Service
public class AppraisalServiceImpl extends ServiceImpl<AppraisalMapper, Appraisal> implements AppraisalService{

    @Resource
    private AppraisalMapper appraisalMapper;



}
