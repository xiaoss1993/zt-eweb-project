package com.zt.eweb.modular.rbac.client.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户修改手机号参数
 *
 * @author xxm
 * @since 2022/6/19
 */
@Data
@Accessors(chain = true)
@Schema(title = "用户修改手机号参数")
public class UserChangePhoneParam {

    @Schema(description = "新手机号")
    @NotBlank(message = "手机号不可为空")
    // @Pattern(regexp = "")
    private String phone;

    @Schema(description = "原手机号验证码")
    @NotBlank(message = "原手机号验证码不可为空")
    private String oldCaptcha;

    @Schema(description = "新手机号验证码")
    @NotBlank(message = "新手机号验证码不可为空")
    private String newCaptcha;

}
