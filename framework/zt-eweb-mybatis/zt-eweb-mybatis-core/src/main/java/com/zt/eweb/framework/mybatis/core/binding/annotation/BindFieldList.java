
package com.zt.eweb.framework.mybatis.core.binding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定字段集合（1-n）
 *
 * @author
 * @version v2.0
 * @date 2019/1/21
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BindFieldList {

  /***
   * 绑定的Entity类
   * @return
   */
  Class entity();

  /***
   * 绑定字段
   * @return
   */
  String field();

  /***
   * JOIN连接条件
   * @return
   */
  String condition();

  /**
   * EntityList排序，示例 `id:DESC,age:ASC`
   *
   * @return
   */
  String orderBy() default "";

  /**
   * 分隔符，用于拆解拼接存储的多个id值
   *
   * @return
   */
  String splitBy() default "";
}