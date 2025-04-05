package com.zt.eweb.modular.rbac.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import java.util.Date;
import lombok.Data;

/**
 * 系统验证码DO
 *
 * @author haoxin
 * @date 2021-02-02
 **/
@Data
@TableName("sys_captcha")
public class SysCaptchaDO extends BaseEntity<String> {

    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private Date expireTime;

}
