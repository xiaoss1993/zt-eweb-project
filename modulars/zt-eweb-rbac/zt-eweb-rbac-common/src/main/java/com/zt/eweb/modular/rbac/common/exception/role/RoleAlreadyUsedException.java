package com.zt.eweb.modular.rbac.common.exception.role;

import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.ROLE_ALREADY_USED;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * @author xxm
 * @since 2020/4/29 14:42
 */
public class RoleAlreadyUsedException extends BizException implements Serializable {

    private static final long serialVersionUID = 3704932788916299672L;

    public RoleAlreadyUsedException() {
        super(ROLE_ALREADY_USED, "该角色下分配了用户，您无法将其删除.");
    }

}
