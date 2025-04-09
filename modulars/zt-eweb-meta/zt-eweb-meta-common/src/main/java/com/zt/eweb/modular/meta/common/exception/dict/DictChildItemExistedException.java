package com.zt.eweb.modular.meta.common.exception.dict;

import com.zt.eweb.framework.common.exception.BizException;
import com.zt.eweb.modular.meta.common.code.BspErrorCodes;
import java.io.Serializable;

/**
 * 
 * @since 2020/4/16 22:08
 */
public class DictChildItemExistedException extends BizException implements Serializable {

    private static final long serialVersionUID = -3964173905076738575L;

    public DictChildItemExistedException() {
        super(BspErrorCodes.CHILD_ITEM_EXISTED, "存在字典子项，您无法将其删除。");
    }

}
