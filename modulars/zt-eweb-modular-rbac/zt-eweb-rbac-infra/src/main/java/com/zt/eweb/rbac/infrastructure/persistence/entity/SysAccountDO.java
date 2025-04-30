package com.zt.eweb.rbac.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.utils.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 用户Token DO
 *
 * 
 * @date 2021-02-09
 **/
@Data
@TableName("sys_account")
public class SysAccountDO extends BaseDO {

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

    /**
     * 盐
     */
    private String salt;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireTime;
}
