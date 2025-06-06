package com.zt.eweb.rbac.domain.model.log;

/**
 * 日志-Repository接口
 *
 * 
 * @date 2021-02-02
 **/
public interface LogRepository {

    /**
     * 保存日志
     *
     * @param log
     */
    void store(Log log);
}
