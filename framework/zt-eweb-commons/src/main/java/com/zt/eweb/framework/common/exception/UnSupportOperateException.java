package com.zt.eweb.framework.common.exception;

import static com.zt.eweb.framework.common.code.CommonErrorCode.UN_SUPPORTED_OPERATE;

import java.io.Serializable;

/**
 * 不支持的操作异常
 *
 * 
 * @since 2022/7/27
 */
public class UnSupportOperateException extends ErrorCodeRuntimeException implements Serializable {

    public UnSupportOperateException(String message) {
        super(UN_SUPPORTED_OPERATE, message);
    }

    public UnSupportOperateException() {
        super(UN_SUPPORTED_OPERATE, "不支持的操作异常");
    }

}
