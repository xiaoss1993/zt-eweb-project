
package com.zt.eweb.framework.mybatis.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * LabelValue键值对形式的VO（用于构建显示名Name-存储值Value形式的结果）
 *
 * @author
 * @version v2.0
 * @date 2019/1/4
 */
@Getter
@Setter
@Accessors(chain = true)
public class LabelValue implements Serializable {

  private static final long serialVersionUID = -2358161241655186720L;
  /**
   * label: 显示值
   */
  private String label;
  /**
   * value: 存储值
   */
  private Object value;
  /**
   * 扩展值
   */
  private Object ext;
  /**
   * 父级ID (用于构建tree结构)
   */
  @JsonIgnore
  private Object parentId;
  /**
   * 子节点集合
   */
  private List<LabelValue> children;

  public LabelValue() {
  }

  public LabelValue(String label, Object value) {
    this.value = value;
    this.label = label;
  }

}
