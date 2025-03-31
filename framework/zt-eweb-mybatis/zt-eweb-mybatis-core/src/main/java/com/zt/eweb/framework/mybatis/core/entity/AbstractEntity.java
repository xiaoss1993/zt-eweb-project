
package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zt.eweb.framework.mybatis.core.config.Cons;
import com.zt.eweb.framework.mybatis.core.util.BeanUtils;
import com.zt.eweb.framework.mybatis.core.util.ContextHolder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Entity抽象父类
 *
 * @author Yangzhao@dibo.ltd
 * @version v2.0 Copyright © diboot.com
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class AbstractEntity<T extends Serializable> implements Serializable {

  private static final long serialVersionUID = 10202L;

  /**
   * 默认主键字段id，类型为String型雪花id
   */
  @TableId(type = IdType.ASSIGN_ID)
  private T id;

  /**
   * 获取主键值
   *
   * @return
   */
  @JsonIgnore
  public Object getPrimaryKeyVal() {
    String pk = ContextHolder.getIdFieldName(this.getClass());
    if (pk == null) {
      return null;
    }
    if (Cons.FieldName.id.name().equals(pk)) {
      return getId();
    }
    return BeanUtils.getProperty(this, pk);
  }

  /**
   * Entity对象转为String
   *
   * @return
   */
  @Override
  public String toString() {
    return this.getClass().getName() + ":" + this.getId();
  }
}
