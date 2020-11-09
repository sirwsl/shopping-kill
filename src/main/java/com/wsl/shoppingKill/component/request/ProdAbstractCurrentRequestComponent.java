package com.wsl.shoppingKill.component.request;

import com.alibaba.fastjson.JSONObject;

import com.wsl.shoppingKill.obj.bo.UserBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 线上获取当前登录用户信息
 * TODO 还未实现，后面需要自己实现
 * @author wsl
 */
@Slf4j
@Component
@Profile({"pro"})
public class ProdAbstractCurrentRequestComponent extends AbstractCurrentRequestComponent {

    /**
     * 获取当前登陆用户信息
     * @return 当前登录用户信息
     */
    @Override
    public UserBO getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader(REQUEST_HEADER_TOKEN);
        token = StringUtils.isNotBlank(token)?token:request.getHeader(TOKEN);
        if (StringUtils.isBlank(token) || "null".equals(token) || "undefined".equals(token)) {
            return null;
        }
        String pc = stringRedisTemplate.opsForValue().get(token);
        String result = StringUtils.isNotBlank(pc) ? pc : stringRedisTemplate.opsForValue().get(token);
        if (StringUtils.isNotBlank(result)) {
            UserBO oaUserBO = JSONObject.parseObject(result, UserBO.class);
            if (oaUserBO == null) {
                log.info("redis里tokenInfo为空");
                return null;
            }
            return oaUserBO;
        }

        return null;
    }
}
