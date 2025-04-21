package com.zt.eweb.rbac.client.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 账号名登录Command
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Data
public class AccountLoginCommand {

    /**
     * 账号名
     */
    @NotBlank(message="账号名不能为空")
    private String accountName;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
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
