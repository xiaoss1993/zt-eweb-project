package com.zt.eweb.framework.spring.aop;

import com.google.common.base.Stopwatch;
import com.zt.eweb.framework.common.annotation.CountTime;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 方法耗时工具类
 *
 * 
 * @since 2020/12/22
 */
@Aspect
@Slf4j
@Component
public class CountTimeAop {

    @Around("@annotation(countTime)")
    public Object doAround(ProceedingJoinPoint pjp, CountTime countTime) throws Throwable {
        // 创建的时候就开始计时
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object obj = pjp.proceed();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        // 停止计时，然后计算时长.单位为毫秒.
        long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        log.info("方法 [{}] 花费时间：{}ms", methodName, (elapsed));
        return obj;
    }

}
