
package com.zt.eweb.framework.mybatis.core.holder.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口定义类wrapper
 *
 * @author Mazhicheng
 * @version v2.0
 * @date 2020/02/28
 */
@Getter
@Setter
public class RestApiWrapper implements Serializable {

  private static final long serialVersionUID = 4355669647175573884L;
  /**
   * 类名
   */
  private String className;
  /**
   * 类别标题
   */
  private String title;
  /**
   * 子节点
   */
  private List<RestApi> children;
  /**
   * 接口类上的注解
   */
  @JsonIgnore
  private Annotation annotation;

  public RestApiWrapper() {
  }

  public RestApiWrapper(String className, String title, Annotation annotation) {
    this.className = className;
    this.title = title;
    this.annotation = annotation;
  }

  /**
   * 添加节点
   *
   * @param child
   */
  public void addChild(RestApi child) {
    if (this.children == null) {
      this.children = new ArrayList<>();
    }
    this.children.add(child);
  }

}