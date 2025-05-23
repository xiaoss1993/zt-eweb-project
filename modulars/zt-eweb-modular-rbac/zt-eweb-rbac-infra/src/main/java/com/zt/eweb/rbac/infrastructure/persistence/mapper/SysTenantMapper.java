package com.zt.eweb.rbac.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysTenantDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 租户Mapper
 *
 *
 * @date 2021-02-14
 **/
@Mapper
public interface SysTenantMapper extends BaseMapper<SysTenantDO> {

    /**
     * 分页查询租户
     *
     * @param params
     * @return
     */
    IPage<SysTenantDO> queryPage(IPage<SysTenantDO> page, @Param("params") Map<String, Object> params);
}
