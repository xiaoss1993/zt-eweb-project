
package com.zt.eweb.framework.mybatis.core.binding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 绑定Entity 注解定义（1-1）
 *
 * @author
 * @version v2.0
 * @date 2019/1/21
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BindEntity {

  /***
   * 对应的service类
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
}