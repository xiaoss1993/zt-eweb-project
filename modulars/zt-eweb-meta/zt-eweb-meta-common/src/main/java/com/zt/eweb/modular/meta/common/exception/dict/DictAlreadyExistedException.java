package com.zt.eweb.modular.meta.common.exception.dict;

import com.zt.eweb.framework.common.exception.BizException;
import com.zt.eweb.modular.meta.common.code.BspErrorCodes;
import java.io.Serializable;

/**
 * 
 * @since 2020/4/10 15:14
 */
public class DictAlreadyExistedException extends BizException implements Serializable {

    public DictAlreadyExistedException() {
        super(BspErrorCodes.DICTIONARY_ALREADY_EXISTED, "字典已经存在.");
    }

}
