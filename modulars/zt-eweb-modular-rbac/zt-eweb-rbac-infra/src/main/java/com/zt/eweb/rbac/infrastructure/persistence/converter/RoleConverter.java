package com.zt.eweb.rbac.infrastructure.persistence.converter;

import com.zt.eweb.framework.common.domain.StatusEnum;
import com.zt.eweb.framework.common.exception.XTException;
import com.zt.eweb.rbac.domain.model.permission.PermissionId;
import com.zt.eweb.rbac.domain.model.role.Role;
import com.zt.eweb.rbac.domain.model.role.RoleCode;
import com.zt.eweb.rbac.domain.model.role.RoleId;
import com.zt.eweb.rbac.domain.model.role.RoleName;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysPermissionDO;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysRoleDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色Converter
 *
 * 
 * @date 2021-02-19
 **/
public class RoleConverter {

    public static Role toRole(SysRoleDO sysRoleDO, List<SysPermissionDO> sysPermissionDOList) {
        if(sysRoleDO == null) {
            throw new XTException("未找到角色");
        }
        List<PermissionId> permissionIds = null;
        if(sysPermissionDOList != null && !sysPermissionDOList.isEmpty()) {
            permissionIds = new ArrayList<>();
            for(SysPermissionDO sysPermissionDO : sysPermissionDOList) {
                permissionIds.add(new PermissionId(sysPermissionDO.getId()));
            }
        }
        Role role = new Role(new RoleId(sysRoleDO.getId()),new RoleCode(sysRoleDO.getRoleCode()),new RoleName(sysRoleDO.getRoleName()),
                sysRoleDO.getRemarks(), StatusEnum.getStatusEnum(sysRoleDO.getStatus()),permissionIds);
        return role;
    }

    public static SysRoleDO fromRole(Role role) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setId(role.getRoleId() == null?null:role.getRoleId().getId());
        sysRoleDO.setRoleCode(role.getRoleCode()==null?null:role.getRoleCode().getCode());
        sysRoleDO.setRoleName(role.getRoleName()==null?null:role.getRoleName().getName());
        sysRoleDO.setRemarks(role.getRemarks());
        sysRoleDO.setStatus(role.getStatus()==null?null:role.getStatus().getValue());
        return sysRoleDO;
    }

}
