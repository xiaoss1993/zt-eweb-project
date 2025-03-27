
package com.zt.eweb.framework.mybatis.core.holder.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * rest接口定义
 *
 * @author
 * @version 2.0
 * @date 2019-12-03
 */
@Getter
@Setter
@Accessors(chain = true)
public class RestApi implements Serializable {

  private static final long serialVersionUID = -372368864183815006L;

  /**
   * 类别
   */
  private String category;

  /**
   * 接口名称
   */
  private String apiName;

  /**
   * 接口Method
   */
  private String apiMethod;

  /**
   * 接口URI
   */
  private String apiUri;

  /**
   * path路径参数
   */
  private List<Param> pathVariables;
  /**
   * url参数
   */
  private List<Param> urlParams;

}