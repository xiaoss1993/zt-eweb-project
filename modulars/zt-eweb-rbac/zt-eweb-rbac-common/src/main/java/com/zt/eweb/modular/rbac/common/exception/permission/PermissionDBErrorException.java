package com.zt.eweb.modular.rbac.common.exception.permission;

import static com.zt.eweb.modular.rbac.common.code.IamErrorCode.PERMISSION_DB_ERROR;

import com.zt.eweb.framework.common.exception.BizException;
import java.io.Serializable;

/**
 * @author xxm
 * @since 2020/5/7 18:01
 */
public class PermissionDBErrorException extends BizException implements Serializable {

    private static final long serialVersionUID = -2698918595713722011L;

    public PermissionDBErrorException() {
        super(PERMISSION_DB_ERROR, "用户没有权限.");
    }

}
