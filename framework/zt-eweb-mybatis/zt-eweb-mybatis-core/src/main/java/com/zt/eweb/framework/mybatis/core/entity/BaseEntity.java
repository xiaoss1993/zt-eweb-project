
package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zt.eweb.framework.mybatis.core.config.Cons;
import com.zt.eweb.framework.mybatis.core.util.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Entity基础父类
 *
 * @author
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity<T extends Serializable> extends AbstractEntity<T> {

  private static final long serialVersionUID = 10203L;

  /**
   * 默认逻辑删除标记，is_deleted=0有效
   */
  @TableLogic
  @JsonIgnore
  @TableField(value = Cons.COLUMN_IS_DELETED, select = false)
  private boolean deleted = false;

  /**
   * 默认记录创建时间字段，新建时由数据库赋值
   */
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  /***
   * Entity对象转为map
   * @return
   */
  public Map<String, Object> toMap() {
    String jsonStr = JSON.stringify(this);
    return JSON.toMap(jsonStr);
  }

}