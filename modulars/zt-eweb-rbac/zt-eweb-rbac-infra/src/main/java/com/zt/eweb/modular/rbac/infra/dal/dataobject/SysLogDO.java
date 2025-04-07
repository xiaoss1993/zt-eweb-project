package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseModel;
import lombok.Data;

/**
 * 日志DO
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@Data
@TableName("sys_log")
public class SysLogDO extends BaseModel {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 租户ID
     */
    private String tenantId;
}
