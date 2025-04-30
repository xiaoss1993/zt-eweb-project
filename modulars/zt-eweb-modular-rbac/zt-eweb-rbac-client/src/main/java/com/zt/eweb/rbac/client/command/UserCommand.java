package com.zt.eweb.rbac.client.command;

import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import com.zt.eweb.framework.common.utils.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 用户Command
 *
 * 
 * @date 2021-02-20
 **/
@Data
public class UserCommand {

    /**
     * id
     */
    @NotBlank(message="用户id不能为空" , groups = UpdateGroup.class)
    private String id;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空" , groups = AddGroup.class)
    private String userName;

    /**
     * 手机号
     */
    @NotBlank(message="手机号不能为空" , groups = AddGroup.class)
    private String mobile;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空" , groups = AddGroup.class)
    private String email;

    /**
     * 角色列表
     */
    private List<String> roleIdList;
}
