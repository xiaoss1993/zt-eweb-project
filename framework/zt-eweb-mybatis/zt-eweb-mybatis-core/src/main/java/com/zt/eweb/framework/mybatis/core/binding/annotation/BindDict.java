
package com.zt.eweb.framework.mybatis.core.binding.annotation;

import java.lang.annotation.*;

/**
 * 绑定字典注解
 *
 * @author
 * @version v2.0
 * @date 2019/1/21
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BindDict {

  /***
   * 绑定数据字典类型
   * @return
   */
  String type();

  /***
   * 数据字典项取值字段
   * @return
   */
  String field() default "";
}