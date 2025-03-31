
package com.zt.eweb.framework.mybatis.core.exception;

import com.zt.eweb.framework.mybatis.core.util.S;

import java.util.HashMap;
import java.util.Map;

/**
 * 无效使用异常类 InvalidUsageException
 *
 * @author : wind
 * @version : v2.3.1
 * @date 2021/08/23
 */
public class InvalidUsageException extends RuntimeException {

  /**
   * 自定义内容提示
   *
   * @param msg
   */
  public InvalidUsageException(String msg, Object... args) {
    super(S.format(msg, args));
  }

  /**
   * 自定义内容提示
   *
   * @param msg
   */
  public InvalidUsageException(Throwable ex, String msg, Object... args) {
    super(S.format(msg, args), ex);
  }

  /**
   * 转换为Map
   *
   * @return
   */
  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>(8);
    map.put("code", getCode());
    map.put("msg", getMessage());
    return map;
  }

  private int getCode() {
    return 5005;
  }

}
