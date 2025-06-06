package com.zt.eweb.framework.common.function;

/**
 * 参数获取服务(必须有实现类)
 *
 * 
 * @since 2022/5/1
 */
public interface ParamService {

    /**
     * 获取 参数值, 如果未启用, 返回空
     */
    String getValue(String key);

}
