package com.zt.eweb.rbac.client.command;

import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 注册租户Command
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Data
public class RegisterTenantCommand {

    /**
     * 租户名
     */
    @NotBlank(message="租户名不能为空")
    private String tenantName;

    /**
     * 租户编码
     */
    @NotBlank(message="租户编码不能为空")
    private String tenantCode;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空")
    private String userName;

    /**
     * 手机号
     */
    @NotBlank(message="手机号不能为空", groups = AddGroup.class)
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message="验证码不能为空")
    private String captcha;

    /**
     * uuid
     */
    @NotBlank(message="uuid不能为空")
    private String uuid;
}
