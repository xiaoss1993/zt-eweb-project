package com.zt.eweb.rbac.adapter.util.log;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 *
 * @date 2021-02-02
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
