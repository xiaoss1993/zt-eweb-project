package com.zt.eweb.rbac.infrastructure.persistence.converter;

import com.zt.eweb.framework.common.domain.StatusEnum;
import com.zt.eweb.framework.common.exception.XTException;
import com.zt.eweb.rbac.domain.model.permission.*;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysPermissionDO;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * 权限Converter
 *
 * 
 * @date 2021-02-16
 **/
public class PermissionConverter {

    public static Permission toPermission(SysPermissionDO sysPermissionDO, SysPermissionDO parentPermissionDO, List<SysPermissionDO> subPermissionDOList) {
        if(sysPermissionDO == null) {
            throw new XTException("未找到权限");
        }
        MenuUrl menuUrl = null;
        if(sysPermissionDO.getMenuUrl() != null) {
            menuUrl = new MenuUrl(sysPermissionDO.getMenuUrl());
        }
        Permission parent = PermissionConverter.toPermission(parentPermissionDO);
        List<Permission> subPermissions = null;
        if(subPermissionDOList != null && !subPermissionDOList.isEmpty()) {
            subPermissions = new ArrayList<>();
            for(SysPermissionDO subSysPermissionDO : subPermissionDOList) {
                Permission subPermission = PermissionConverter.toPermission(subSysPermissionDO);
                subPermissions.add(subPermission);
            }
        }
        Permission permission = new Permission(new PermissionId(sysPermissionDO.getId()),new PermissionName(sysPermissionDO.getPermissionName()), PermissionTypeEnum.getMenuTypeEnum(sysPermissionDO.getPermissionType()),
                PermissionLevelEnum.getMenuLevelEnum(sysPermissionDO.getPermissionLevel()),sysPermissionDO.getMenuIcon(), toPermissionCodes(Collections.singletonList(sysPermissionDO.getPermissionCodes())),sysPermissionDO.getOrderNum(),
                menuUrl, parent, StatusEnum.getStatusEnum(sysPermissionDO.getStatus()), subPermissions);
        return permission;
    }

    public static Permission toPermission(SysPermissionDO sysPermissionDO) {
        if(sysPermissionDO == null) {
            return null;
        }
        MenuUrl menuUrl = null;
        if(sysPermissionDO.getMenuUrl() != null) {
            menuUrl = new MenuUrl(sysPermissionDO.getMenuUrl());
        }
        Permission permission = new Permission(new PermissionId(sysPermissionDO.getId()),new PermissionName(sysPermissionDO.getPermissionName()), PermissionTypeEnum.getMenuTypeEnum(sysPermissionDO.getPermissionType()),
                PermissionLevelEnum.getMenuLevelEnum(sysPermissionDO.getPermissionLevel()),sysPermissionDO.getMenuIcon(), toPermissionCodes(Collections.singletonList(sysPermissionDO.getPermissionCodes())),sysPermissionDO.getOrderNum(),
                menuUrl, null, StatusEnum.getStatusEnum(sysPermissionDO.getStatus()), null);
        return permission;
    }

    public static PermissionCodes toPermissionCodes(List<String> permsList) {
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        PermissionCodes permissionCodes = null;
        if(!permsSet.isEmpty()) {
            permissionCodes = new PermissionCodes(permsSet);
        }
        return permissionCodes;
    }

    public static SysPermissionDO fromPermission(Permission permission){
        SysPermissionDO sysPermissionDO = new SysPermissionDO();
        sysPermissionDO.setId(permission.getPermissionId() == null ? null : permission.getPermissionId().getId());
        sysPermissionDO.setPermissionName(permission.getPermissionName()==null?null:permission.getPermissionName().getName());
        sysPermissionDO.setParentId(permission.getParent() == null ? null : permission.getParent().getPermissionId().getId());
        sysPermissionDO.setMenuIcon(permission.getMenuIcon());
        sysPermissionDO.setMenuUrl(permission.getMenuUrl() == null ? null : permission.getMenuUrl().getUrl());
        sysPermissionDO.setOrderNum(permission.getOrderNum());
        sysPermissionDO.setPermissionCodes(permission.getPermissionCodes()== null ? null:permission.getPermissionCodes().getCodesString());
        sysPermissionDO.setPermissionLevel(permission.getPermissionLevel()==null?null:permission.getPermissionLevel().getValue());
        sysPermissionDO.setPermissionType(permission.getPermissionType()==null?null:permission.getPermissionType().getValue());
        sysPermissionDO.setStatus(permission.getStatus()==null?null:permission.getStatus().getValue());
        return sysPermissionDO;
    }
}
