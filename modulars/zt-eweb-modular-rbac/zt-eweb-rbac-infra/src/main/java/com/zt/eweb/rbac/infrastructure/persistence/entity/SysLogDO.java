package com.zt.eweb.rbac.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.utils.BaseDO;
import lombok.Data;

/**
 * 日志DO
 *
 *
 * @date 2021-02-02
 **/
@Data
@TableName("sys_log")
public class SysLogDO extends BaseDO {

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
