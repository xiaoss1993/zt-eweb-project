
package com.zt.eweb.framework.mybatis.core.holder.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 收集接口注解
 *
 * @author JerryMa
 * @version v2.2.1
 * @date 2021/4/23 Copyright © diboot.com
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CollectThisApi {

  /**
   * 接口标题/名称
   *
   * @return
   */
  String name();

  /**
   * 分类
   *
   * @return
   */
  String category() default "default";
}