package com.zt.eweb.framework.spring.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 切面工具类
 *
 */
@UtilityClass
public class AopUtil {

    /**
     * 获取切面方法上的注解
     */
    public <T extends Annotation> T getMethodAnnotation(JoinPoint joinPoint, Class<T> annotationClass) {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            return null;
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(annotationClass);
        }
        return null;
    }

}
