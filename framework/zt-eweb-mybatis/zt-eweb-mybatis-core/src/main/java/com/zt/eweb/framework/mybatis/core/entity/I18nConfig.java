
package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 国际化配置
 *
 * @author wind
 * @version v3.0.0
 * @date 2022-10-12
 */
@Deprecated
@Getter
@Setter
@Accessors(chain = true)
@TableName("dbt_i18n_config")
public class I18nConfig extends BaseEntity<String> {

  /**
   * type字段的关联字典
   */
  public static final String DICT_I18N_TYPE = "I18N_TYPE";
  private static final long serialVersionUID = 11501L;
  /**
   * 租户ID
   */
  @JsonIgnore
  @TableField
  private String tenantId;

  /**
   * 类型
   */
  private String type;

  /**
   * 语言
   */
  @NotNull
  private String language;

  /**
   * 资源标识
   */
  @NotNull
  private String code;
  /**
   * 内容
   */
  @NotNull
  private String content;

}
