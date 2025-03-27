
package com.zt.eweb.framework.mybatis.core.data.copy;

import java.lang.annotation.*;

/**
 * 拷贝字段时的非同名字段处理
 *
 * @author
 * @version v2.1
 * @date 2020/06/04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Accept {

  /**
   * 接收来源对象的属性名
   *
   * @return
   */
  String name();

  /**
   * source该字段有值时是否覆盖
   *
   * @return
   */
  boolean override() default false;
}