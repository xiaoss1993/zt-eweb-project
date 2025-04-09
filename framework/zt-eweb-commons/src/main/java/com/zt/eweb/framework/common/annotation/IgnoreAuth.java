package com.zt.eweb.framework.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略鉴权, 可以放在controller上和方法上，同时使用时，以方法上的为准
 *
 * @author aeizzz
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IgnoreAuth {

    /**
     * 是否忽略请求鉴权
     */
    boolean ignore() default true;

    /**
     * 只要登录就忽略权限校验, login值为true是ignore将不生效
     */
    boolean login() default false;


}
