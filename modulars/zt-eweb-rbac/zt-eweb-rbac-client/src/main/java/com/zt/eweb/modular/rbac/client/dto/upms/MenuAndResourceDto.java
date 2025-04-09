package com.zt.eweb.modular.rbac.client.dto.upms;

import com.zt.eweb.modular.rbac.client.dto.permission.PermMenuDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户菜单及资源权限返回类
 *
 * @author xxm
 * @since 2021/8/25
 */
@Data
@Accessors(chain = true)
@Schema(title = "菜单及资源权限数据类")
public class MenuAndResourceDto {

    @Schema(description = "资源权限码集合")
    private List<String> resourcePerms;

    @Schema(description = "菜单")
    private List<PermMenuDto> menus;

}
