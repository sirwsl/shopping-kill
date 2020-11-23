package com.wsl.shoppingKill.controller.common;

import com.wsl.shoppingKill.common.Result;
import com.wsl.shoppingKill.common.exception.Exceptions;
import com.wsl.shoppingKill.obj.exception.TokenRuntimeException;
import io.jsonwebtoken.ExpiredJwtException;
import io.lettuce.core.RedisException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;


/**
 * @author wsl
 */
@RestControllerAdvice(annotations = RestController.class)
@ResponseStatus(HttpStatus.ACCEPTED)
@Slf4j
public class ControllerAdvice {

    private static String getOutMsg(final Throwable e) {
        if (e != null) {

            return e.getClass().getCanonicalName();
        }
        return "";
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    public static void main(String[] args) {
        System.out.println(getOutMsg(new SQLException("aaa")));
    }

    @ExceptionHandler(BindException.class)
    public Result<Object> exception(BindException e) {
        log.error("参数异常：{}", Exceptions.getStackTraceAsString(e));
        return new Result<>(Result.PARAM_ERROR, getOutMsg(e), Objects.requireNonNull(e.getFieldError()).getDefaultMessage(), null);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> exception(MissingServletRequestParameterException e) {
        log.error("参数异常：{}", Exceptions.getStackTraceAsString(e));
        return new Result<>(Result.PARAM_ERROR, getOutMsg(e), "缺少必要参数", null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> exception(MethodArgumentNotValidException e) {
        log.error("数据验证未通过：{}", Exceptions.getStackTraceAsString(e));
        return new Result<>(Result.PARAM_ERROR, getOutMsg(e), Objects.requireNonNull(e.getBindingResult().getFieldError())
                .getDefaultMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> exception(HttpMessageNotReadableException e) {
        log.error("参数异常：{}", Exceptions.getStackTraceAsString(e));
        return new Result<>(Result.PARAM_ERROR, getOutMsg(e), "参数错误", null);
    }

    @ExceptionHandler(RedisException.class)
    public Result<Object> exception(RedisException e) {
        log.error("redis链接异常：{}", Exceptions.getStackTraceAsString(e));
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "服务器繁忙", null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Object> exception(IllegalArgumentException e) {
        log.error("参数异常：{}", Exceptions.getStackTraceAsString(e));
        return Result.paramError(getOutMsg(e), "缺少必要参数");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<Object> exception(HttpMediaTypeNotSupportedException e) {
        log.error("入参异常", e);
        Map<String, String> parameters = Objects.requireNonNull(e.getContentType()).getParameters();
        for (String s : parameters.keySet()) {
            log.error("param:{}", s);
        }
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String s = request.getRequestURL().toString();
        log.error("url:{}", s);
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "服务器开小差了", null);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Object> exception(DuplicateKeyException e) {
        log.error("服务器异常", e);
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "数据库数据异常", null);
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> exception(Exception e) {
        log.error("服务器异常", e);
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "服务器开小差了", null);
    }

    @ExceptionHandler(SpelEvaluationException.class)
    public Result<Object> exception(SpelEvaluationException e) {
        log.error("服务器异常", e);
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "服务器开小差了", null);
    }

    @ExceptionHandler(NullPointerException.class)
    public Result<Object> exception(NullPointerException e) {
        log.error("服务器异常", e);
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "参数异常NPE", null);
    }

    @ExceptionHandler(RemoteException.class)
    public Result<Object> exception(RemoteException e) {
        log.error("调用远程服务器常：{}", Exceptions.getStackTraceAsString(e));
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "服务器开小差了", null);
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result<Object> exception(IllegalStateException e) {
        log.error("参数异常：{}", Exceptions.getStackTraceAsString(e));
        return Result.paramError(getOutMsg(e), "请求参数不正确");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Object> exception(MethodArgumentTypeMismatchException e) {
        log.error("参数异常：{}", Exceptions.getStackTraceAsString(e));
        return Result.paramError(getOutMsg(e), "请求参数不正确");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Result<Object> exception(ExpiredJwtException e) {
        log.error("服务器异常", e);
        return new Result<>(Result.SERVER_ERROR, getOutMsg(e), "登录已经过期，Token令牌失效", null);
    }

    @ExceptionHandler(TokenRuntimeException.class)
    public Result<Object> tokenRuntimeException(TokenRuntimeException e) {
        log.error("token校验不通过",e);
        return new Result<>(Result.UNLOGIN,getOutMsg(e), e.getMsg(),null);
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public Result<Object> unsupportedEncodingException(UnsupportedEncodingException e) {
        log.error("账号名称编码异常",e);
        return Result.paramError(getOutMsg(e), "账号异常，存在非正常编码字符");
    }



    /**
     * 异常信息通知内部类
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class TipError {
        //异常时间
        Date date;
        //异常次数
        Integer count;
        //异常信息描述
        String tipMsg;
        //异常信息前缀部分
        String tipMsgPre;
        //异常已通知次数
        Integer noticeCount;


    }
}
