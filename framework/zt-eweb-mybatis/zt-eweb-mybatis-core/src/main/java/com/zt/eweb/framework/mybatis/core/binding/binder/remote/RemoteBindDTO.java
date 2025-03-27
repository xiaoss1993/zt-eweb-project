
package com.zt.eweb.framework.mybatis.core.binding.binder.remote;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 远程绑定DTO定义
 *
 * @author JerryMa
 * @version v2.4.0
 * @date 2021/11/2 Copyright © diboot.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class RemoteBindDTO implements Serializable {

  private static final long serialVersionUID = -3339006060332345228L;

  private String entityClassName;
  private List<String> selectColumns;
  private String refJoinCol;
  private Collection<?> inConditionValues;
  private List<String> additionalConditions;
  private String orderBy;
  private String resultType;

  public RemoteBindDTO() {
  }

  public RemoteBindDTO(Class<?> entityClass) {
    this.entityClassName = entityClass.getName();
  }

}
