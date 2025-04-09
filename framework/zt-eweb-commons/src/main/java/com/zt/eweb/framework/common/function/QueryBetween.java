package com.zt.eweb.framework.common.function;

/**
 * 查询生成器范围查询标识接口
 * 
 * @since 2024/1/15
 */
public interface QueryBetween {

    /**
     * 获取开始值
     */
    Object getStart();

    /**
     * 获取结束值
     */
    Object getEnd();
}
