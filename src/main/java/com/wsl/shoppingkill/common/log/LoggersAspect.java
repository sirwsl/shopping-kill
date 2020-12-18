package com.wsl.shoppingkill.common.log;

import com.wsl.shoppingkill.common.util.IpUtils;
import com.wsl.shoppingkill.component.request.AbstractCurrentRequestComponent;
import com.wsl.shoppingkill.domain.Loggers;
import com.wsl.shoppingkill.obj.bo.UserBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
public class LoggersAspect {


    @Resource
    private AbstractCurrentRequestComponent abstractCurrentRequestComponent;

    /**
     *定义切点 @Pointcut 在注解的位置切入代码
     **/
    @Pointcut("@annotation( com.wsl.shoppingkill.common.log.MyLog)")
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

            //将参数所在的数组转换成json
            String num =  "0";
            try {
                if (StringUtils.isNotBlank(myLog.value())){
                    num = AspectSupport.getKeyValue(joinPoint, myLog.value()).toString();
                }
            }catch (Exception e){
                log.error("日志切面获取数据异常{}",e.getLocalizedMessage());
            }


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
