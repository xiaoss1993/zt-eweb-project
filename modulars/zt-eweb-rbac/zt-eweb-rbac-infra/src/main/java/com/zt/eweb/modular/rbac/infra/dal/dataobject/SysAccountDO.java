package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户Token DO
 *
 * @author haoxin
 * @date 2021-02-09
 **/
@Data
@TableName("sys_account")
public class SysAccountDO implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

}
