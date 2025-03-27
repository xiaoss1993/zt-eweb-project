
package com.zt.eweb.framework.mybatis.core.exception;

import com.zt.eweb.framework.mybatis.core.util.S;
import com.zt.eweb.framework.mybatis.core.vo.Status;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的业务异常类 BusinessException (json形式返回值同JsonResult，便于前端统一处理)
 *
 * @author : wee
 * @version : v2.0
 * @Date 2019-07-11  11:10
 */
public class BusinessException extends RuntimeException {

  private Integer code;

  /**
   * 错误的状态
   */
  private Status status;

  /**
   * 默认：操作失败
   */
  public BusinessException() {
    super(Status.FAIL_OPERATION.label());
    this.status = Status.FAIL_OPERATION;
  }

  /**
   * 自定义状态码
   */
  public BusinessException(Status status) {
    super(status.label());
    this.status = status;
  }

  /**
   * 自定义状态码和异常
   */
  public BusinessException(Status status, Throwable ex) {
    super(status.label(), ex);
    this.status = status;
  }

  /**
   * 自定义状态码和内容提示
   */
  public BusinessException(Status status, String msg, Object... args) {
    super(status.label() + ": " + S.format(msg, args));
    this.status = status;
  }

  /**
   * 自定义状态码和内容提示
   */
  public BusinessException(int code, String msg) {
    super(msg);
    this.code = code;
  }

  /**
   * 自定义内容提示
   */
  public BusinessException(String msg, Object... args) {
    super(S.format(msg, args));
    this.status = Status.FAIL_OPERATION;
  }

  /**
   * 自定义内容提示
   */
  public BusinessException(Status status, Throwable ex, String msg, Object... args) {
    super(status.label() + ": " + S.format(msg, args), ex);
    this.status = status;
  }

  /**
   * 自定义内容提示
   */
  public BusinessException(int code, Throwable ex, String msg, Object... args) {
    super(S.format(msg, args), ex);
    this.code = code;
  }

  /**
   * 转换为Map
   */
  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>(8);
    map.put("code", getCode());
    map.put("msg", getMessage());
    return map;
  }

  /**
   * 获取status，以便复用
   */
  public Status getStatus() {
    return this.status;
  }

  private Integer getCode() {
    if (this.code == null && this.status != null) {
      this.code = this.status.code();
    }
    return this.code;
  }

}
