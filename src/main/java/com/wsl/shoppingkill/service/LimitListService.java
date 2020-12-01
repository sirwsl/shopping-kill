package com.wsl.shoppingkill.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingkill.domain.LimitList;
import com.wsl.shoppingkill.obj.param.LimitListParam;

import java.util.List;

/**
 * @author wangShilei
 */
public interface LimitListService extends IService<LimitList> {

    /**
     * 添加黑名单
     * @author : WangShiLei
     * @date : 2020/11/7 5:18 下午
     * @param limitListParam:
     * @return 插入结果
     **/
    boolean addBlackList(LimitListParam limitListParam);

    /**
     * 获取手机号黑名单
     * @author : WangShiLei
     * @date : 2020/11/8 12:30 下午
     * @param page :
     * @param num :
     * @return IPage<LimitListParam>
     **/
    IPage<LimitList> getBlackListForPhone(Integer page, Integer num);


    /**
     * 获取IP黑名单
     * @author : WangShiLei
     * @date : 2020/11/8 12:30 下午
     * @param page :
     * @param num :
     * @return IPage<LimitListParam>
     **/
    IPage<LimitList> getBlackListForIp(Integer page, Integer num);

    /**
     * 更具number（IP or Phone）获取
     * @author : WangShiLei
     * @date : 2020/11/8 5:19 下午
     * @param num :
     * @param type :
     * @return LimitList
     **/
    List<LimitList> getBlackListByNumber(String num,Integer type);
}
