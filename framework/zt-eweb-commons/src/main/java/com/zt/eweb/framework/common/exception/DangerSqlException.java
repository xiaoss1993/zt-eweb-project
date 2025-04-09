package com.zt.eweb.framework.common.exception;

import static com.zt.eweb.framework.common.code.CommonErrorCode.DANGER_SQL;

/**
 * SQL相关异常
 *
 * 
 * @since 2023/3/9
 */
public class DangerSqlException extends BizException {

    public DangerSqlException(String msg) {
        super(DANGER_SQL, msg);
    }

    public DangerSqlException() {
        super(DANGER_SQL, "危险SQL异常");
    }

}
