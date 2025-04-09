package com.zt.eweb.modular.rbac.client.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xxm
 * @since 2021/9/29
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户部门关联")
public class UserDeptParam {

    @Schema(description = "用户id")
    @NotNull(message = "用户id不可为空")
    private Long userId;

    @Schema(description = "部门id集合")
    private List<Long> deptIds;

}
