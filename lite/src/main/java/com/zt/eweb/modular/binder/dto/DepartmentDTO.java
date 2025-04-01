
package com.zt.eweb.modular.binder.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zt.eweb.framework.mybatis.core.binding.query.BindQuery;
import com.zt.eweb.framework.mybatis.core.binding.query.Comparison;
import com.zt.eweb.framework.mybatis.core.binding.query.Strategy;
import com.zt.eweb.modular.binder.entity.Department;
import com.zt.eweb.modular.binder.entity.Organization;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Department DTO
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
public class DepartmentDTO implements Serializable {

  private static final long serialVersionUID = 8670003133709715087L;

  private Long parentId;

  @BindQuery(comparison = Comparison.LIKE, strategy = Strategy.IGNORE_EMPTY)
  private String name;

  // 绑定join查询
  @BindQuery(comparison = Comparison.STARTSWITH, strategy = Strategy.INCLUDE_NULL, entity = Organization.class, column = "name", condition = "this.org_id=id")
  private String orgName;

  // 绑定join查询
  @BindQuery(entity = Department.class, column = "name", condition = "this.parent_id=id")
  private String parentName;

  // 多绑定or连接
  @BindQuery(comparison = Comparison.CONTAINS, column = "name")
  @BindQuery(comparison = Comparison.STARTSWITH, column = "`character`")
  @BindQuery(comparison = Comparison.ENDSWITH, entity = Organization.class, column = "name", condition = "this.org_id=id")
  private String search;

  // 查询单个日期
  @BindQuery(comparison = Comparison.GE, column = "create_time")
  private LocalDateTime createTime;

  @BindQuery(comparison = Comparison.LT, column = "create_time")
  private LocalDateTime createTimeEnd;

  @BindQuery(column = "parent_id", comparison = Comparison.IN)
  private List<Long> parentIds;

  @BindQuery(comparison = Comparison.LIKE)
  @TableField("`character`")
  //@TableField
  private String character;

  public LocalDateTime getCreateTimeEnd() {
    return createTime != null ? createTime.plusDays(1) : null;
  }

}
