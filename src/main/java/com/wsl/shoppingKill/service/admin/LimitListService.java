package com.wsl.shoppingKill.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wsl.shoppingKill.domain.LimitList;
import com.wsl.shoppingKill.obj.param.LimitListParam;

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

}
