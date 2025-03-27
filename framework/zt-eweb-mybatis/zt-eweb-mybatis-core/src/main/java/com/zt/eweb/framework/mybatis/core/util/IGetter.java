
package com.zt.eweb.framework.mybatis.core.util;

import java.io.Serializable;

/**
 * getter方法接口定义
 *
 * @author
 * @version v2.0
 * @date 2019/1/15
 */
@FunctionalInterface
public interface IGetter<T> extends Serializable {

  Object apply(T source);
}
