/*
 * Copyright (c) 2006-2017, Yunnan Sanjiu Network technology Co., Ltd.
 *
 * All rights reserved.
 */
package com.wsl.shoppingkill.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 前端控制器返回封装类，除了第三方调用（cloud和http等请求）外，
 * 本系统内部尽量在controller层使用，不建议传递到service层（复用麻烦）
 *
 * @author wsl
 * @param <T>
 */
public class Result<T> {

    /**成功**/
    public static final int SUCCESS = 0;

    /**鉴权失败,需要登录**/
    public static final int UNLOGIN = 1000;
     /**存在危险字符**/
    public static final int DANGER_CHAR = 1001;
    /**需要账号验证**/
    public static final int NEED_VERIFY = 1003;
     /**参数校验失败**/
    public static final int PARAM_VALIDATE_FAILED = 2000;
     /**参数错误**/
    public static final int PARAM_ERROR = 2001;
     /**暂无数据**/
    public static final int NO_DATA = 2002;
     /**内部出错**/
    public static final int SERVER_ERROR = 5000;
     /**调用接口返回空**/
    public static final int SERVER_RETURN_NULL = 2002;
     /**调用webservice出错**/
    public static final int WEBSERVER_ERROR = 5001;
     /** 调用服务出错**/
    public static final int RETURN_ERROR = 2003;

    private int code = 0;

    private String msg = "";

    private String userMsg = "";


    private T data = null;

    public Result() {
    }

    /**
     * {@link #success()}
     *
     * @param code:
     */

    public Result(int code) {
        this.code = code;
    }

    /**
     * {@link #success(Object)}
     *
     * @param data:
     */

    public Result(T data) {
        this.data = data;
    }

    /**
     * {@link #success()}
     * {@link #error()}
     *
     * @param code:
     * @param msg:
     * @param userMsg:
     * @param data:
     */

    public Result(int code, String msg, String userMsg, T data) {
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, String userMsg) {
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @JsonIgnore
    public boolean isSuccess() {
        return this.code == SUCCESS;
    }

    public static <T> Result<T> success(String msg, String userMsg, T t) {
        return new Result<>(Result.SUCCESS, msg, userMsg, t);
    }

    public static <T> Result<T> success(T t) {
        return new Result<>(Result.SUCCESS, "SUCCESS", "操作成功", t);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error(String msg, String userMsg, T t) {
        return new Result<>(Result.SERVER_ERROR, msg, userMsg, t);
    }

    public static <T> Result<T> error(String msg, String userMsg) {
        return new Result<>(Result.SERVER_ERROR, msg, userMsg, null);
    }

    public static <T> Result<T> error(T t) {
        return new Result<>(Result.SERVER_ERROR, "", "", t);
    }

    public static <T> Result<T> error() {
        return new Result<>(Result.SERVER_ERROR, "", "", null);
    }

    public static <T> Result<T> paramError(String msg, String userMsg) {
        return new Result<>(Result.PARAM_ERROR, msg, userMsg, null);
    }

    public static <T> Result<T> paramError(String msg, String userMsg, T t) {
        return new Result<>(Result.PARAM_ERROR, msg, userMsg, t);
    }

    public static <T> Result<T> paramError(T t) {
        return new Result<>(Result.PARAM_ERROR, "参数错误", "", t);
    }

    public static <T> Result<T> noData() {
        return new Result<>(Result.NO_DATA, "", "暂无数据", null);
    }


    /**
     * <p>
     * 全部改用拦截器
     */
    @Deprecated
    public static <T> Result<T> unloving(String msg, String userMsg) {
        return new Result<>(Result.UNLOGIN, msg, userMsg, null);
    }

    /**
     * <p>
     * 全部改用拦截器
     */
    @Deprecated
    public static <T> Result<T> unloving(String msg, String userMsg, T t) {
        return new Result<>(Result.UNLOGIN, msg, userMsg, t);
    }


    /**
     * <p>
     * 全部改用拦截器
     */
    @Deprecated
    public static <T> Result<T> unloving(T t) {
        return new Result<>(Result.UNLOGIN, "", "", t);
    }

    public static <T> Result<T> needVerify() {
        return new Result<>(Result.NEED_VERIFY, "", "检测到您最近修改过密码，请重新登录", null);
    }

}
