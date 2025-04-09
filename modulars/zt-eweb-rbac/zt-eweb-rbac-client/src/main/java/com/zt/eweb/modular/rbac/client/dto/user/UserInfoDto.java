package com.zt.eweb.modular.rbac.client.dto.user;

import com.zt.eweb.framework.common.entity.UserDetail;
import com.zt.eweb.framework.common.rest.dto.BaseDto;
import com.zt.eweb.framework.dataperm.annotation.sensitive.SensitiveInfo;
import com.zt.eweb.modular.rbac.common.code.UserStatusCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xxm
 * @since 2020/4/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "用户信息")
public class UserInfoDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 5881350477107722635L;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    @SensitiveInfo(SensitiveInfo.SensitiveType.PASSWORD)
    private String password;

    @Schema(description = "手机号")
    @SensitiveInfo(SensitiveInfo.SensitiveType.MOBILE_PHONE)
    private String phone;

    @Schema(description = "邮箱")
    @SensitiveInfo(SensitiveInfo.SensitiveType.EMAIL)
    private String email;

    @Schema(description = "终端id列表")
    private List<Long> clientIds;

    @Schema(description = "是否管理员")
    private boolean administrator;

    /**
     * @see UserStatusCode
     */
    @Schema(description = "账号状态")
    private String status;


    public UserDetail toUserDetail() {
        return new UserDetail().setId(this.getId())
            .setPassword(this.password)
            .setUsername(this.getUsername())
            .setName(this.name)
            .setAdmin(this.administrator)
            .setClientIds(this.clientIds)
            .setStatus(this.status);
    }

}
