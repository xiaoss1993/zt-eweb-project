package com.zt.eweb.module.infra.dal.mysql.db;

import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 *
 */
@Mapper
public interface DataSourceConfigMapper extends BaseCrudMapper<DataSourceConfigDO> {
}
