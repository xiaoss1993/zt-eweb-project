package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysPower;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysMenuMapper extends BaseCrudMapper<SysPower> {
    @Select("select * from sys_power where enable = #{enable} order by sort")
    List<SysPower> findAllByStatusOrderBySort(Boolean enable);
}
