package com.zt.eweb.rbac.application.impl;

import com.zt.eweb.framework.common.utils.TenantContext;
import com.zt.eweb.rbac.application.assembler.PermissionDTOAssembler;
import com.zt.eweb.rbac.client.PermissionQueryService;
import com.zt.eweb.rbac.client.dto.PermissionDTO;
import com.zt.eweb.rbac.domain.model.permission.Permission;
import com.zt.eweb.rbac.domain.model.permission.PermissionId;
import com.zt.eweb.rbac.domain.model.permission.PermissionRepository;
import com.zt.eweb.rbac.domain.model.permission.PermissionTypeEnum;
import com.zt.eweb.rbac.domain.model.role.RoleCode;
import com.zt.eweb.rbac.domain.model.tenant.TenantId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 权限查询服务实现类
 *
 *
 * @date 2021-05-10
 **/
@Service
public class PermissionQueryServiceImpl implements PermissionQueryService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> listAllPermission() {
        List<Permission> permissionList;
        if(TenantId.PLATFORM_TENANT.equals(TenantContext.getTenantId())) {
            permissionList = permissionRepository.queryList(new HashMap<>());
        } else {
            permissionList = permissionRepository.queryList(new RoleCode(RoleCode.TENANT_ADMIN));
        }
        return PermissionDTOAssembler.getPermissionList(permissionList);
    }

    @Override
    public List<PermissionDTO> listAllMenu() {
        List<Permission> permissionList;
        if(TenantId.PLATFORM_TENANT.equals(TenantContext.getTenantId())) {
            permissionList = permissionRepository.queryList(new HashMap<>());
        } else {
            permissionList = permissionRepository.queryList(new RoleCode(RoleCode.TENANT_ADMIN));
        }
        return PermissionDTOAssembler.getMenuList(permissionList);
    }

    @Override
    public PermissionDTO getById(String id) {
        Permission permission = permissionRepository.find(new PermissionId(id));
        return PermissionDTOAssembler.fromPermission(permission);
    }

    @Override
    public List<PermissionDTO> getUserMenuTree(Set<String> permissionIds) {
        if(permissionIds == null) {
            return null;
        }
        return getAllMenuList(permissionIds);
    }

    /**
     * 获取所有菜单列表
     */
    private List<PermissionDTO> getAllMenuList(Set<String> menuIdList){
        //查询根菜单列表
        List<PermissionDTO> menuList = queryListParentId(Permission.ROOT_ID, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 通过父级id获取权限
     *
     * @param parentId
     * @param menuIdList
     * @return
     */
    private List<PermissionDTO> queryListParentId(String parentId, Set<String> menuIdList) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId",parentId);
        List<PermissionDTO> menuList = PermissionDTOAssembler.getPermissionList(permissionRepository.queryList(params));
        if(menuIdList == null){
            return menuList;
        }
        List<PermissionDTO> userMenuList = new ArrayList<>();
        for(PermissionDTO menu : menuList){
            if(menuIdList.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 递归
     */
    private List<PermissionDTO> getMenuTreeList(List<PermissionDTO> menuList, Set<String> menuIdList){
        List<PermissionDTO> subMenuList = new ArrayList<>();
        for(PermissionDTO entity : menuList){
            //目录
            if(PermissionTypeEnum.CATALOG.getValue().equals(entity.getPermissionType())){
                entity.setSubList(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}
