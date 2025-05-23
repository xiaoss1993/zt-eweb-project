package com.zt.eweb.rbac.client;

import com.zt.eweb.rbac.client.dto.PermissionDTO;

import java.util.List;
import java.util.Set;

/**
 * 权限查询服务接口
 *
 * 
 * @date 2021-05-10
 **/
public interface PermissionQueryService {

    /**
     * 所有权限
     *
     * @return
     */
    List<PermissionDTO> listAllPermission();

    /**
     * 所有菜单（不保存按钮）
     *
     * @return
     */
    List<PermissionDTO> listAllMenu();

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    PermissionDTO getById(String id);

    /**
     * 获取权限树
     *
     * @param permissionIds
     * @return
     */
    List<PermissionDTO> getUserMenuTree(Set<String> permissionIds);
}
