package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志Mapper
 *
 * @author haoxin
 * @date 2021-01-23
 **/
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogDO> {

}
