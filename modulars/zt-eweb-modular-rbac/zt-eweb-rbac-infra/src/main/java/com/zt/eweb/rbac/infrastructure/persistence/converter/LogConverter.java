package com.zt.eweb.rbac.infrastructure.persistence.converter;

import com.zt.eweb.rbac.domain.model.log.Log;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysLogDO;

/**
 * 日志转换类
 *
 *
 * @date 2021-02-02
 **/
public class LogConverter {

    public static SysLogDO fromLog(Log log) {
        SysLogDO sysLogDO = new SysLogDO();
        sysLogDO.setUserName(log.getUserName()==null?null:log.getUserName().getName());
        sysLogDO.setIp(log.getIp());
        sysLogDO.setMethod(log.getMethod());
        sysLogDO.setOperation(log.getOperation());
        sysLogDO.setTime(log.getTime());
        return sysLogDO;
    }
}
