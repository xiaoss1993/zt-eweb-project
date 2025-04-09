package com.zt.eweb.modular.rbac.common.exception.user;

import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.NONE_PHONE_AND_EMAIL;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * 用户手机号和邮箱不可都为空的异常
 *
 * @author xxm
 * @since 2020/5/7 18:30
 */
public class UserNonePhoneAndEmailException extends BizException implements Serializable {

    private static final long serialVersionUID = -6866507370268138197L;

    public UserNonePhoneAndEmailException() {
        super(NONE_PHONE_AND_EMAIL, "用户的电话和电子邮件必须包含一个");
    }

}
