package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserRoleDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联Mapper
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Mapper
public interface SysUserRoleMapper extends BaseCrudMapper<SysUserRoleDO> {

    /**
     * 根据角色ID，批量删除
     */
    int deleteByRoleIds(List<String> roleIds);

    /**
     * 根据用户ID，批量删除
     */
    int deleteByUserIds(List<String> userIds);
}
