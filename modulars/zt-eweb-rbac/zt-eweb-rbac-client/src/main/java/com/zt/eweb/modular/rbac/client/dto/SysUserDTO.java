package com.zt.eweb.modular.rbac.client.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDTO implements Serializable {
    private String keyword;
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String nickName;

    private Long deptId;
    @TableField(exist = false)
    private SysDeptDto dept;
    @TableField(exist = false)
    private String deptName;
    private Integer sex;

    private String phone;

    private Integer enable;
    private String email;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


    @TableField(exist = false)
    private String roleIds;

    @TableField(exist = false)
    private String dataRoleId;

    public QueryWrapper<SysUserDTO> queryWrapper() {
        QueryWrapper<SysUserDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(nickName), "nick_name", nickName);
        queryWrapper.eq(sex != null, "sex", sex);
        queryWrapper.and(StringUtils.isNotEmpty(keyword),
                likeQueryWrapper -> likeQueryWrapper.like("user_name", keyword)
                        .or().like("nick_name", keyword));
        return queryWrapper;
    }
}
