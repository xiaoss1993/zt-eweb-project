package com.zt.eweb.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/1 15:40 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/1      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Aspect
@Component
public class MyBatisAspect {


  @Around("execution(* com.zt.eweb.*.mapper.*.*(..))") // 指定切入点为所有Mapper的方法调用
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    // 在方法执行前添加日志等操作
    System.out.println("Before method: " + joinPoint.getSignature());
    Object result = joinPoint.proceed(); // 继续执行方法体中的代码
    // 在方法执行后添加日志等操作
    System.out.println("After method: " + joinPoint.getSignature());
    return result; // 返回结果给调用者
  }
}
