
package com.zt.eweb.framework.mybatis.core.binding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定Entity集合注解（1-n）
 *
 * @author
 * @version v2.0
 * @date 2019/1/21
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BindEntityList {

  /***
   * 对应的entity类
   * @return
   */
  Class entity();

  /***
   * JOIN连接条件
   * @return
   */
  String condition();

  /**
   * 深度绑定
   *
   * @return
   */
  boolean deepBind() default false;

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