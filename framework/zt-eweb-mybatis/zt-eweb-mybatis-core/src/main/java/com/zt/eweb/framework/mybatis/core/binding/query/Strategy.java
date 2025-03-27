
package com.zt.eweb.framework.mybatis.core.binding.query;

/**
 * 查询策略（针对空值等的查询处理策略）
 *
 * @author JerryMa
 * @version v2.2.1
 * @date 2021/5/7 Copyright © diboot.com
 */
public enum Strategy {
  /**
   * 忽略空字符串""
   */
  IGNORE_EMPTY,
  /**
   * 空字符串""参与查询
   */
  INCLUDE_EMPTY,
  /**
   * null参与构建isNull
   */
  INCLUDE_NULL,
}
