package com.wsl.shoppingKill.component.JwtToken;

import com.wsl.shoppingKill.common.util.JwtTokenUtils;
import com.wsl.shoppingKill.obj.bo.UserBO;
import org.springframework.stereotype.Component;

/**
 * @author WangShilei
 * @date 2020/11/23-10:47
 **/

@Component
public class JwtTokenComponent {
    /**
     * token过期时间
     */

    /**
     * 设置Token令牌
     * @param userBO:
     * @return String:
     */
    public String generatorToken(UserBO userBO,int expire){
        return JwtTokenUtils.generatorToken(userBO,expire);
    }

    /**
     * 获取登录信息
     * @author wangShilei
     * @date 2020/11/23 11:06
     * @param token :
     * @return com.wsl.shoppingKill.obj.bo.UserBO
     */
    public UserBO stringInfoFromToken(String token){
        return JwtTokenUtils.getTokenInfo(token);
    }
}
