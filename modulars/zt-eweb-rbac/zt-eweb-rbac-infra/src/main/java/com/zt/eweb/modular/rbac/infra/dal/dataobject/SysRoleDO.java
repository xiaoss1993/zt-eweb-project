package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseModel;
import lombok.Data;

/**
 * 角色DO
 *
 * @author haoxin
 * @date 2021-02-15
 **/
@Data
@TableName("sys_role")
public class SysRoleDO extends BaseModel {

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;
}
