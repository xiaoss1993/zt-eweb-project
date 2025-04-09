package com.zt.eweb.modular.rbac.common.exception.role;

import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.ROLE_NOT_EXISTED;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * 角色不存在
 *
 * @author xxm
 * @since 2020/5/7 18:04
 */
public class RoleNotExistedException extends BizException implements Serializable {

    private static final long serialVersionUID = -6651799569179960110L;

    public RoleNotExistedException() {
        super(ROLE_NOT_EXISTED, "角色不存在.");
    }

}
