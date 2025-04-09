package com.zt.eweb.modular.meta.common.exception.dict;

import com.zt.eweb.framework.common.exception.BizException;
import com.zt.eweb.modular.meta.common.code.BspErrorCodes;
import java.io.Serializable;

/**
 * 
 * @since 2020/4/21 11:54
 */
public class DictItemAlreadyUsedException extends BizException implements Serializable {

    public DictItemAlreadyUsedException() {
        super(BspErrorCodes.DICTIONARY_ITEM_ALREADY_USED, "词典项目已被使用.");
    }

}
