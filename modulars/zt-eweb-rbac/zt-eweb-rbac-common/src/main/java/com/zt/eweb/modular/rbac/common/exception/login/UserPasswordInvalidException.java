package com.zt.eweb.modular.rbac.common.exception.login;


import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.USER_PASSWORD_INVALID;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * 用户密码不正确异常
 *
 * @author xxm
 * @since 2020/5/7 18:16
 */
public class UserPasswordInvalidException extends BizException implements Serializable {

    private static final long serialVersionUID = 6321083408077778554L;

    public UserPasswordInvalidException() {
        super(USER_PASSWORD_INVALID, "用户密码不正确");
    }

}
