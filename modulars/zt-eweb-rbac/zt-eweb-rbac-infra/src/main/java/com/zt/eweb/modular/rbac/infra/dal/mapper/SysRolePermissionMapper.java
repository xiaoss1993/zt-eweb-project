package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysRolePermissionDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关联Mapper
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionDO> {

    /**
     * 根据角色ID，批量删除
     */
    int deleteByRoleIds(List<String> roleIds);

    /**
     * 根据权限ID，批量删除
     */
    int deleteByPermissionIds(List<String> permissionIds);
}
