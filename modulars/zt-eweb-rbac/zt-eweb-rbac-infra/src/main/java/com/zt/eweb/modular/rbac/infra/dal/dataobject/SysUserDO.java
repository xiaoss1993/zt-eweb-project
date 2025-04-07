package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseModel;
import lombok.Data;

/**
 * 用户DO
 *
 * @author haoxin
 * @date 2021-02-09
 **/
@Data
@TableName("sys_user")
public class SysUserDO extends BaseModel {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 帐户
     */
    private String accountId;


    private String password;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 关联id
     */
    private String linkId;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 租户编码
     */
    @TableField(exist = false)
    private String tenantCode;

    /**
     * 租户名称
     */
    @TableField(exist = false)
    private String tenantName;

    /**
     * 手机号
     */
    @TableField(exist = false)
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(exist = false)
    private String email;
}
