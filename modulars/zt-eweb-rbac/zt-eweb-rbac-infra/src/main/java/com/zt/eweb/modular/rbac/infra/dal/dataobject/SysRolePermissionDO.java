package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import lombok.Data;

/**
 * 角色权限关联DO
 *
 * @author haoxin
 * @date 2021-02-15
 **/
@Data
@TableName("sys_role_permission")
public class SysRolePermissionDO extends BaseEntity<String> {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    private String remarks;
}
