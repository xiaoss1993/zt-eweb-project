
package com.zt.eweb.framework.mybatis.core.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 排序参数 DTO
 *
 * @author wind
 * @version v3.0.0
 * @date 2022/07/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class SortParamDTO<ID extends Serializable> implements Serializable {

  private static final long serialVersionUID = 30303L;

  /**
   * 操作对象ID
   */
  @NotNull(message = "id 不能为空")
  private ID id;

  /**
   * 新序号
   */
  @NotNull(message = "newSortId 不能为空")
  private Long newSortId;

  /**
   * 旧序号
   */
  private Long oldSortId;

  /**
   * 新的父节点ID（未改化传原父节点ID）
   * <p>
   * Tree 结构数据：应指定 parentIdField，及传递 newParentId；当跨层级时无需传递 oldSortId
   */
  private ID newParentId;

}
