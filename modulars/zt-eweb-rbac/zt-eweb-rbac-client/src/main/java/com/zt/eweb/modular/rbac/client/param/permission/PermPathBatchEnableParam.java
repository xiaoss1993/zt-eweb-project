package com.zt.eweb.modular.rbac.client.param.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求权限批量启停用
 *
 * @author xxm
 * @since 2022/6/6
 */
@Data
@Accessors(chain = true)
@Schema(title = "请求权限批量启停用")
public class PermPathBatchEnableParam {

    @NotEmpty
    @Schema(description = "权限码集合")
    private List<Long> permPathIds;

    @Schema(description = "是否启用")
    private boolean enable;

}
