package com.zt.eweb.framework.common.exception;

import static com.zt.eweb.framework.common.code.CommonErrorCode.VALIDATE_PARAMETERS_ERROR;

import java.io.Serializable;

/**
 * 验证失败异常
 */
public class ValidationFailedException extends BizException implements Serializable {

    private static final long serialVersionUID = -3973809880035275189L;

    public ValidationFailedException(String detail) {
        super(VALIDATE_PARAMETERS_ERROR, "验证参数错误" + System.lineSeparator() + detail);
    }

    public ValidationFailedException() {
        super();
    }

}
