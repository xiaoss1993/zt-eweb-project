package com.zt.eweb.framework.common.constant.enums;

import java.io.Serializable;

/**
 * @author laker
 */
public interface IEnum<T extends Serializable> {
    T getValue();
}
