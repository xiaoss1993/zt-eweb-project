
package com.zt.eweb.modular.binder.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zt.eweb.framework.mybatis.core.binding.annotation.BindEntity;
import com.zt.eweb.framework.mybatis.core.binding.annotation.BindEntityList;
import com.zt.eweb.framework.mybatis.core.data.copy.Accept;
import com.zt.eweb.modular.binder.entity.Department;
import com.zt.eweb.modular.binder.entity.Organization;
import com.zt.eweb.modular.binder.entity.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Department VO
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
public class DepartmentVO {

  private static final long serialVersionUID = -4849732665419794547L;

  @Accept(name = "id")
  @TableField
  private String id;

  @TableField
  private String parentId;

  @TableField(exist = false)
  private String name;

  @TableField
  private String orgId;

  // 关联Entity
  @BindEntity(entity = Department.class, condition = "this.parent_id=id") // AND ...
  private Department department;

  // 关联Entity，赋值给VO
  @BindEntity(entity = Organization.class, condition = "this.org_id=id", deepBind = true) // AND ...
  private OrganizationVO organizationVO;

  @BindEntityList(entity = Department.class, condition = "parent_id=this.id AND this.name IS NOT NULL", deepBind = true)
  // AND ...
  private List<DepartmentVO> children;

  @BindEntityList(entity = User.class, condition = "this.id = department_id") // AND ...
  private List<User> userList;


  @BindEntityList(entity = User.class, condition = "this.id = department_id ") // AND ...
  private List<User> userListAll;


}