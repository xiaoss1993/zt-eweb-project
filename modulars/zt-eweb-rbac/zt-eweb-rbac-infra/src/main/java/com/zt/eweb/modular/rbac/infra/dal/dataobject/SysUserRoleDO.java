package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联DO
 *
 * @author haoxin
 * @date 2021-02-15
 **/
@Data
@TableName("sys_user_role")
public class SysUserRoleDO implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    private String remarks;
}
