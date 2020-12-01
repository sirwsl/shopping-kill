package com.wsl.shoppingkill.common.log;

/**
 * @author WangShilei
 * @date 2020/11/9-18:44
 **/
public class ExpressionRootObject {
    private final Object object;
    private final Object[] args;

    public ExpressionRootObject(Object object, Object[] args) {
        this.object = object;
        this.args = args;
    }

    public Object getObject() {
        return object;
    }

    public Object[] getArgs() {
        return args;
    }
}
