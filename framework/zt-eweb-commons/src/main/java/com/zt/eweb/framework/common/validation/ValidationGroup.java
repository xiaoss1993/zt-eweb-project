package com.zt.eweb.framework.common.validation;

/**
 * 校验分组
 *
 * 
 * @since 2021/5/7
 */
public interface ValidationGroup {

    /**
     * 参数校验分组：增加
     */
    @interface add {

    }

    /**
     * 参数校验分组：编辑
     */
    @interface edit {

    }

    /**
     * 参数校验分组：删除
     */
    @interface delete {

    }

    /**
     * 参数校验分组：查询
     */
    @interface query {

    }

}
