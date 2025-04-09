package com.zt.eweb.framework.common.exception;

import static com.zt.eweb.framework.common.code.CommonErrorCode.DATA_OUT_OF_DATE;

import java.io.Serializable;

/**
 * 乐观锁异常
 *
 * 
 * @since 2020/4/15 14:11
 */
public class OptimisticLockException extends SystemException implements Serializable {

    private static final long serialVersionUID = -1605410024618499135L;

    public OptimisticLockException() {
        super(DATA_OUT_OF_DATE, "数据不存在或者已过期");
    }

}
