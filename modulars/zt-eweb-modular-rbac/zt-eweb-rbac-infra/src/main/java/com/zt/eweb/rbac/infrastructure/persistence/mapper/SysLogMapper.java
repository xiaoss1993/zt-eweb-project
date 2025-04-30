package com.zt.eweb.rbac.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志Mapper
 *
 * 
 * @date 2021-01-23
 **/
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogDO> {

}
