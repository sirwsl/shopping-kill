package com.wsl.shoppingkill.service;

import com.wsl.shoppingkill.obj.param.UserParam;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author : WangShiLei
 * @date : 2020/11/23 10:58 下午
 **/
public interface LoginService {

    /**
     * 登录验证
     * @author wangShilei
     * @date 2020/11/23 10:59 下午
     * @param userParam :
     * @param response :
     * @return boolean
     * @throws UnsupportedEncodingException:
     */
    boolean login(HttpServletResponse response, UserParam userParam) throws UnsupportedEncodingException;
}
