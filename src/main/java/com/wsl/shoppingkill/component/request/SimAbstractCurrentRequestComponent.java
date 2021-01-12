package com.wsl.shoppingkill.component.request;

import com.wsl.shoppingkill.obj.bo.UserBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 本地获取当前登录用户信息
 * @author wsl
 */
@Slf4j
@Component
@Profile({"dev","test"})
public class SimAbstractCurrentRequestComponent extends AbstractCurrentRequestComponent {

    @Override
    public UserBO getCurrentUser() {
        UserBO userBO = new UserBO();
        return userBO.setId(1L).setFlag(UserBO.ADMIN).setName("王世磊");
    }
}
