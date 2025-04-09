package com.zt.eweb.modular.meta.common.exception.dict;

import com.zt.eweb.framework.common.exception.BizException;
import com.zt.eweb.modular.meta.common.code.BspErrorCodes;
import java.io.Serializable;

/**
 * 
 * @since 2020/4/21 11:54
 */
public class DictItemNotExistedException extends BizException implements Serializable {

    public DictItemNotExistedException() {
        super(BspErrorCodes.DICTIONARY_ITEM_NOT_EXISTED, "字典项不存在.");
    }

}
