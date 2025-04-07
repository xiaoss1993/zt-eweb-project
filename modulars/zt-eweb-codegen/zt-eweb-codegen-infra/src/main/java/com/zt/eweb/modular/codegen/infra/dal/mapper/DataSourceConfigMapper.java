package com.zt.eweb.modular.codegen.infra.dal.mapper;

import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.codegen.infra.dal.entity.DataSourceConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源
 *
 */
@Mapper
public interface DataSourceConfigMapper extends BaseCrudMapper<DataSourceConfig> {

}
