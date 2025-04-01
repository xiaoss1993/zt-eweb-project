
package com.zt.eweb.framework.mybatis.core.binding.annotation;

import java.lang.annotation.*;

/**
 * 国际化翻译
 *
 * @author wind
 * @version v3.0.0
 * @date 2022-10-12
 */
@Deprecated
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BindI18n {

  /**
   * 资源标识 {@link com.zt.eweb.framework.mybatis.core.entity.I18nConfig#getCode()}
   */
  String value();

}
