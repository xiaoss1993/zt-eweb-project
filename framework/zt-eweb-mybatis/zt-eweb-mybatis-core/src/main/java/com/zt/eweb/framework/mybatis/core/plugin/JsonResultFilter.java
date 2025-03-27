
package com.zt.eweb.framework.mybatis.core.plugin;

/**
 * JsonResult结果过滤器
 *
 * @author
 * @version v2.2.0
 * @date 2020/12/16
 */
public interface JsonResultFilter {

  /**
   * 需要全局忽略的字段
   *
   * @return
   */
  <T> void filterData(T data);

}
