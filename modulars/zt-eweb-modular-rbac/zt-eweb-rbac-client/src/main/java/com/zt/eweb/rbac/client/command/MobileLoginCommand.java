package com.zt.eweb.rbac.client.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 手机号登录Command
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Data
public class MobileLoginCommand {

    /**
     * 手机号
     */
    @NotBlank(message="手机号不能为空")
    private String mobile;

    /**
     * 验证码
     */
    @NotBlank(message="验证码不能为空")
    private String verificationCode;

    /**
     * 正确的验证码
     */
    private String correctVerificationCode;

}
