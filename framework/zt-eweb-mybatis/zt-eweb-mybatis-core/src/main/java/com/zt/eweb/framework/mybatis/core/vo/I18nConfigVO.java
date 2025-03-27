
package com.zt.eweb.framework.mybatis.core.vo;

import com.zt.eweb.framework.mybatis.core.binding.annotation.BindDict;
import com.zt.eweb.framework.mybatis.core.entity.I18nConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 国际化配置 VO
 *
 * @author wind
 * @version v3.0.0
 * @date 2022-10-12
 */
@Getter
@Setter
@Accessors(chain = true)
public class I18nConfigVO extends I18nConfig {

  @BindDict(field = "type", type = DICT_I18N_TYPE)
  private LabelValue typeLabel;
}
