package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysRoleDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色Mapper
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {

    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    IPage<SysRoleDO> queryList(IPage<SysRoleDO> page, @Param("params") Map<String, Object> params);

    /**
     * 查询
     *
     * @param params
     * @return
     */
    List<SysRoleDO> queryList(@Param("params") Map<String, Object> params);

    /**
     * 查询用户的所有权限
     *
     * @param userId
     * @return
     */
    List<SysRoleDO> queryUserRole(String userId);
}
