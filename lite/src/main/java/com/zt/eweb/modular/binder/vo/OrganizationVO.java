
package com.zt.eweb.modular.binder.vo;

import com.zt.eweb.framework.mybatis.core.binding.annotation.BindEntity;
import com.zt.eweb.framework.mybatis.core.binding.annotation.BindField;
import com.zt.eweb.modular.binder.entity.Organization;
import com.zt.eweb.modular.binder.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <Description>
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2019/12/06
 */
@Getter
@Setter
@Accessors(chain = true)
public class OrganizationVO {

  private String id;

  private String parentId;

  private String name;

  private String managerId;

  // 关联Entity
  @BindEntity(entity = Organization.class, condition = "this.parent_id=id") // AND ...
  private Organization parentOrg;

  // 关联Entity
  @BindField(entity = Organization.class, field = "name", condition = "this.parent_id=id")
  // AND ...
  private String parentOrgName;

  @BindField(entity = User.class, field = "gender", condition = "this.manager_id=id")
  private String managerGenderLabel;

}
