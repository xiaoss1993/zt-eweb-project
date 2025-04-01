
package com.zt.eweb.modular.binder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zt.eweb.framework.mybatis.core.binding.query.BindQuery;
import com.zt.eweb.framework.mybatis.core.binding.query.Comparison;
import com.zt.eweb.framework.mybatis.core.binding.query.Strategy;
import com.zt.eweb.framework.mybatis.core.data.access.DataAccessCheckpoint;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Department
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class Department extends BaseEntity<String> {

  private static final long serialVersionUID = -4849732665419794547L;

  @BindQuery(comparison = Comparison.EQ, strategy = Strategy.INCLUDE_NULL)
  @TableField
  private String parentId;

  @TableField
  @DataAccessCheckpoint()
  private String orgId;

  @BindQuery(comparison = Comparison.STARTSWITH)
  @TableField
  private String name;

  @TableField("`character`")
  //@TableField
  private String character;

  /**
   * JSON数组
   */
  @TableField(typeHandler = JacksonTypeHandler.class)
  private List<Long> extjsonarr;

  /**
   * JSON对象
   */
  @TableField(typeHandler = JacksonTypeHandler.class)
  private LinkedHashMap<String, Object> extjsonobj;

}
