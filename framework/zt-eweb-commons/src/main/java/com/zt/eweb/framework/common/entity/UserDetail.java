package com.zt.eweb.framework.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户信息类
 *
 * 
 * @since 2021/7/30
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class UserDetail {

    /** 用户id */
    private Long id;

    /** 用户名称 */
    private String name;

    /** 账号 */
    private String username;

    @JsonIgnore
    private transient String password;

    /** 拥有权限的终端列表 */
    private List<Long> clientIds;

    /** 是否管理员 */
    private boolean admin;

    /**
     * 账号状态
     * @see cn.bootx.iam.code.UserStatusCode
     */
    private String status;

}
