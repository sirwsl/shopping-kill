package com.wsl.shoppingkill.common.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.util.StringUtils;


import java.lang.reflect.Method;

/**
 * @author : WangShiLei
 * @date : 2020/11/9 8:43 下午
 **/
public class AspectSupport {
    private static final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    public static Object getKeyValue(JoinPoint joinPoint, String keyExpression) {
        return getKeyValue(joinPoint.getTarget(), joinPoint.getArgs(), joinPoint.getTarget().getClass(),
                ((MethodSignature) joinPoint.getSignature()).getMethod(), keyExpression);
    }

    private static Object getKeyValue(Object object, Object[] args, Class<?> clazz, Method method,
                                      String keyExpression) {
        if (StringUtils.hasText(keyExpression)) {
            EvaluationContext evaluationContext = evaluator.createEvaluationContext(object, clazz, method, args);
            AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clazz);
            return evaluator.key(keyExpression, methodKey, evaluationContext);
        }
        return SimpleKeyGenerator.generateKey(args);
    }
}