package com.zt.eweb.modular.rbac.common.exception.user;


import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.USER_INFO_NOT_EXISTS;

import com.zt.eweb.framework.common.exception.FatalException;

/**
 * 用户信息不存在异常
 *
 * @author xxm
 * @since 2020/5/7 18:29
 */
public class UserInfoNotExistsException extends FatalException {

    public UserInfoNotExistsException() {
        super(USER_INFO_NOT_EXISTS, "用户信息不存在");
    }

}
