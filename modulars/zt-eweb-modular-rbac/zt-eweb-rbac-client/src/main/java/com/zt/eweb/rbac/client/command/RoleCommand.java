package com.zt.eweb.rbac.client.command;

import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import com.zt.eweb.framework.common.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 角色Command
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@Data
public class RoleCommand {

    /**
     * id
     */
    @NotBlank(message="角色id不能为空", groups = UpdateGroup.class)
    private String id;

    /**
     * 角色编码
     */
    @NotBlank(message="角色编码不能为空", groups = AddGroup.class)
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(message="角色名称不能为空", groups = AddGroup.class)
    private String roleName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 权限
     */
    private List<String> permissionIdList;
}
