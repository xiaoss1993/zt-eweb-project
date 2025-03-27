
package com.zt.eweb.framework.mybatis.core.vo;

import com.zt.eweb.framework.mybatis.core.binding.annotation.BindEntityList;
import com.zt.eweb.framework.mybatis.core.entity.Dictionary;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 数据字典的VO，附带子项定义children
 */
@Getter
@Setter
@Accessors(chain = true)
public class DictionaryVO extends Dictionary {

  @BindEntityList(entity = Dictionary.class, condition = "this.type=type AND this.id=parent_id", orderBy = "sort_id:ASC", deepBind = true)
  private List<Dictionary> children;

}
