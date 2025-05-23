package com.zt.eweb.rbac.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.eweb.rbac.domain.model.role.Role;
import com.zt.eweb.rbac.domain.model.permission.PermissionId;
import com.zt.eweb.rbac.domain.model.role.RoleCode;
import com.zt.eweb.rbac.domain.model.role.RoleId;
import com.zt.eweb.rbac.domain.model.role.RoleName;
import com.zt.eweb.rbac.domain.model.role.RoleRepository;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysPermissionDO;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysRoleDO;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysRolePermissionDO;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysPermissionMapper;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysRoleMapper;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysRolePermissionMapper;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysUserRoleMapper;
import com.zt.eweb.rbac.infrastructure.persistence.converter.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色-Repository实现类
 *
 * 
 * @date 2021-02-18
 **/
@Repository
public class RoleRepositoryImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements RoleRepository, IService<SysRoleDO> {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Role find(RoleId roleId) {
        SysRoleDO sysRoleDO = this.getById(roleId.getId());
        if(sysRoleDO == null) {
            return null;
        }
        Role role = RoleConverter.toRole(sysRoleDO, getRolePermission(sysRoleDO.getRoleCode(), sysRoleDO.getId()));
        return role;
    }

    @Override
    public Role find(RoleName roleName) {
        SysRoleDO sysRoleDO = this.getOne(new QueryWrapper<SysRoleDO>().eq("role_name", roleName.getName()));
        if(sysRoleDO == null) {
            return null;
        }
        Role role = RoleConverter.toRole(sysRoleDO, getRolePermission(sysRoleDO.getRoleCode(), sysRoleDO.getId()));
        return role;
    }

    @Override
    public Role find(RoleCode roleCode) {
        SysRoleDO sysRoleDO = this.getOne(new QueryWrapper<SysRoleDO>().eq("role_code", roleCode.getCode()));
        if(sysRoleDO == null) {
            return null;
        }
        Role role = RoleConverter.toRole(sysRoleDO, getRolePermission(sysRoleDO.getRoleCode(), sysRoleDO.getId()));
        return role;
    }

    private List<SysPermissionDO> getRolePermission(String roleCode, String roleId) {
        List<SysPermissionDO> sysPermissionDOList;
        sysPermissionDOList = sysPermissionMapper.queryPermissionByRoleId(roleId);
        return sysPermissionDOList;
    }

    @Override
    public RoleId store(Role role) {
        SysRoleDO sysRoleDO = RoleConverter.fromRole(role);
        this.saveOrUpdate(sysRoleDO);
        String roleId = sysRoleDO.getId();
        //先删除角色与菜单关系
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        sysRolePermissionMapper.deleteByRoleIds(roleIds);
        List<PermissionId> permissionIds = role.getPermissionIds();
        if(permissionIds != null && !permissionIds.isEmpty()) {
            //保存角色与菜单关系
            for(PermissionId permissionId : permissionIds){
                SysRolePermissionDO sysRolePermissionDO = new SysRolePermissionDO();
                sysRolePermissionDO.setPermissionId(permissionId.getId());
                sysRolePermissionDO.setRoleId(roleId);
                sysRolePermissionMapper.insert(sysRolePermissionDO);
            }
        }
        return new RoleId(sysRoleDO.getId());
    }

    @Override
    public void remove(List<RoleId> roleIds) {
        List<String> ids = new ArrayList<>();
        roleIds.forEach(roleId -> {
            ids.add(roleId.getId());
        });
        //删除角色
        this.removeByIds(ids);
        //删除角色与菜单关联
        sysRolePermissionMapper.deleteByRoleIds(ids);
        //删除角色与用户关联
        sysUserRoleMapper.deleteByRoleIds(ids);
    }
}
