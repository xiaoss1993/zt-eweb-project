package com.zt.eweb.modular.rbac.common.exception.role;

import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.ROLE_ALREADY_EXISTED;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * @author xxm
 * @since 2020/4/29 14:37
 */
public class RoleAlreadyExistedException extends BizException implements Serializable {

    private static final long serialVersionUID = -9126473695763034719L;

    public RoleAlreadyExistedException() {
        super(ROLE_ALREADY_EXISTED, "角色已经存在.");
    }

}
