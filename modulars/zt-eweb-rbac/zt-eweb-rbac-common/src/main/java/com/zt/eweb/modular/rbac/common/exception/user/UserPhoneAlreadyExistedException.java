package com.zt.eweb.modular.rbac.common.exception.user;

import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.USER_PHONE_ALREADY_EXISTED;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * 用户手机已存在异常
 *
 * @author xxm
 * @since 2020/5/7 18:25
 */
public class UserPhoneAlreadyExistedException extends BizException implements Serializable {

    private static final long serialVersionUID = -8925952529440870552L;

    public UserPhoneAlreadyExistedException() {
        super(USER_PHONE_ALREADY_EXISTED, "用户手机已存在");
    }

}
