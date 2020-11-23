package com.wsl.shoppingKill.obj.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WangShilei
 * @date 2020/11/23-18:51
 * 异常信息通知内部类
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class TokenRuntimeException extends RuntimeException {

    private Integer code = 401;
    private String msg;

    public TokenRuntimeException(String msg) {
        this.msg = msg;
    }


}