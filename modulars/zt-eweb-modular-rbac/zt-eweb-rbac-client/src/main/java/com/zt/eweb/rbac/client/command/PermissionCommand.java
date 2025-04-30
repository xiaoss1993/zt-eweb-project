package com.zt.eweb.rbac.client.command;

import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import com.zt.eweb.framework.common.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 权限Command
 *
 * 
 * @date 2021-02-18
 **/
@Data
public class PermissionCommand {

    /**
     * id
     */
    @NotBlank(message="ID不能为空" , groups = UpdateGroup.class)
    private String id;

    /**
     * 父级ID
     */
    @NotBlank(message="父级ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String parentId;

    /**
     * 权限名称
     */
    @NotBlank(message="权限名称不能为空", groups = {AddGroup.class})
    private String permissionName;

    /**
     * 权限类型
     */
    @NotBlank(message="权限类型不能为空", groups = {AddGroup.class})
    private String permissionType;

    /**
     * 权限级别
     */
    @NotBlank(message="权限级别不能为空", groups = {AddGroup.class})
    private String permissionLevel;

    /**
     * 权限编码
     */
    private String permissionCodes;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 菜单url
     */
    private String menuUrl;
}
