
package com.zt.eweb.modular.binder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 单位Entity
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2019/1/5
 */
@Getter
@Setter
@Accessors(chain = true)
public class Organization extends BaseEntity<String> {

  private static final long serialVersionUID = -5889309041570465909L;

  @TableField
  private String parentId;

  //@TableField
  private String name;

  @TableField
  private String telphone;

  @TableField
  private String managerId;

}
