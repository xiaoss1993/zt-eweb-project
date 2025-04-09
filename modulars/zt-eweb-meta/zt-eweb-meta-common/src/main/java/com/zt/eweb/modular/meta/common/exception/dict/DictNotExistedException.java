package com.zt.eweb.modular.meta.common.exception.dict;

import com.zt.eweb.framework.common.exception.BizException;
import com.zt.eweb.modular.meta.common.code.BspErrorCodes;
import java.io.Serializable;

/**
 * 
 * @since 2020/4/21 11:53
 */
public class DictNotExistedException extends BizException implements Serializable {

    public DictNotExistedException() {
        super(BspErrorCodes.DICTIONARY_NOT_EXISTED, "字典不存在.");
    }

}
