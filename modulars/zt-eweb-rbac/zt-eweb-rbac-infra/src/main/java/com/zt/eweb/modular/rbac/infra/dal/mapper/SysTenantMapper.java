package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysTenantDO;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 租户Mapper
 *
 * @author haoxin
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
