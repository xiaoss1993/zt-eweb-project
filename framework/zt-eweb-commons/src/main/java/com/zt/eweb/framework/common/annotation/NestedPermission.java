package com.zt.eweb.framework.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 嵌套权限控制注解,需要配合Permission注解一起使用,在Permission注解声明方法调用其他子方法时, 通过添加本数据来忽略子方法执行时的数据权限拦截
 *
 * 
 * @since 2022/1/2
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NestedPermission {

    /**
     * 数据范围权限
     */
    boolean dataScope() default false;

    /**
     * 查询字段权限
     */
    boolean selectField() default false;

}
