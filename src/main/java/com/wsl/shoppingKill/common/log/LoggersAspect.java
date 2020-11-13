package com.wsl.shoppingKill.common.log;

import com.alibaba.fastjson.JSON;
import com.wsl.shoppingKill.common.util.IpUtils;
import com.wsl.shoppingKill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingKill.domain.Loggers;
import com.wsl.shoppingKill.obj.bo.UserBO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/** Log切面类
 * @author WangShilei
 * @date 2020/11/9-10:15
 **/
@Aspect
@Component
public class LoggersAspect {


    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    /**
     *定义切点 @Pointcut 在注解的位置切入代码
     **/
    @Pointcut("@annotation( com.wsl.shoppingKill.common.log.MyLog)")
    public void logPointCut() {
    }

    /**
     *切面 配置通知
     */

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //保存日志
        Loggers loggers = new Loggers();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            //获取请求的类名
            String[] className = joinPoint.getTarget().getClass().getName().split("\\.");

            //请求的参数
            Object[] args = joinPoint.getArgs();
            //将参数所在的数组转换成json
            String params = JSON.toJSONString(args);
            System.err.println(params);
            String num =  AspectSupport.getKeyValue(joinPoint, myLog.value()).toString();


            //保存获取的操作
            loggers.setDetail(myLog.detail()+"->[操作参数："+num+"]"
                    +"->[Class："+className[className.length-1]+"]")
                    .setGrade(myLog.grade());

        }
        UserBO userBO = abstractCurrentRequestComponent.getCurrentUser();
        loggers.setManId(userBO.getId()).setType(userBO.getFlag());
        //获取用户ip地址
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        loggers.setIp(IpUtils.getIP(request));

        //调用service保存SysLog实体类到数据库
        loggers.insert();
    }


}
