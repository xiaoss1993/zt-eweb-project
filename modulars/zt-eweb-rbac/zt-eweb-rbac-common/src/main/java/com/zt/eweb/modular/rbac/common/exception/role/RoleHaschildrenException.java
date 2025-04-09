package com.zt.eweb.modular.rbac.common.exception.role;


import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.ROLE_HAS_CHILD;

import com.zt.eweb.framework.common.exception.BizException;

/**
 * 含有下级角色异常
 * @author xxm
 * @since 2023/12/5
 */
public class RoleHaschildrenException extends BizException {

    public RoleHaschildrenException() {
        super(ROLE_HAS_CHILD, "该角色下分配了用户，您无法将其删除.");
    }
}
