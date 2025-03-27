
package com.zt.eweb.framework.mybatis.core.binding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块注解
 *
 * @author
 * @version v2.0
 * @date 2019/1/21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Module {

  /***
   * 指定模块名
   * @return
   */
  String value();
}