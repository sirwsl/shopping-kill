package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.Experience;

import javax.servlet.http.HttpServletRequest;

/** 体验用户service
 * @author : WangShiLei
 * @date : 2020/11/23 10:58 下午
 **/
public interface ExperienceService extends IService<Experience> {


    /**
     * 体验账号授权
     * @author wangShilei
     * @date 2020/12/18 18:36
     * @param experience :
     * @param request :
     * @return java.lang.String
     */
    String getExp(Experience experience, HttpServletRequest request);
}
