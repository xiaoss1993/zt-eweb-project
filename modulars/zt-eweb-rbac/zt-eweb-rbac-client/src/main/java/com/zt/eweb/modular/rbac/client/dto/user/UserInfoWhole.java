package com.zt.eweb.modular.rbac.client.dto.user;

import com.zt.eweb.modular.rbac.client.dto.dept.DeptDto;
import com.zt.eweb.modular.rbac.client.dto.role.RoleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xxm
 * @since 2021/9/29
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户完整信息")
public class UserInfoWhole {

    @Schema(description = "用户信息")
    private UserInfoDto userInfo;

    @Schema(description = "扩展信息")
    private UserExpandInfoDto userExpandInfo;

    @Schema(description = "角色信息")
    private List<RoleDto> roles;

    @Schema(description = "部门信息")
    private List<DeptDto> deptList;

}
