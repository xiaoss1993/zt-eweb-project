package com.zt.eweb.rbac.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper
 *
 * 
 * @date 2021-01-23
 **/
@Mapper
public interface SysUserMapper extends BaseCrudMapper<SysUserDO> {

    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    IPage<SysUserDO> queryPage(IPage<SysUserDO> page, @Param("params") Map<String, Object> params);

    /**
     * 查询用户
     *
     * @param params
     * @return
     */
    SysUserDO queryUser(@Param("params") Map<String, Object> params);

    /**
     * 查询用户(不包含租户)
     *
     * @param params
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    List<SysUserDO> queryUserNoTenant(@Param("params") Map<String, Object> params);
}
