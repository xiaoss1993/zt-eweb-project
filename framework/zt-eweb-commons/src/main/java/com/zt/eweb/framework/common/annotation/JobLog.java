package com.zt.eweb.framework.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务日志
 *
 * 
 * @since 2022/7/12
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface JobLog {

    /**
     * 是否记录正常日志
     */
    boolean log() default true;

    /**
     * 是否记录异常日志
     */
    boolean errorLog() default true;

}
