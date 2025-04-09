package com.zt.eweb.modular.meta.infra.dal.convert;

import com.zt.eweb.modular.meta.client.dto.dynamicsource.DynamicDataSourceDto;
import com.zt.eweb.modular.meta.client.param.dynamicsource.DynamicDataSourceParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.DynamicDataSource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 动态数据源管理
 *
 * @author xxm
 * @since 2022-09-24
 */
@Mapper
public interface DynamicDataSourceConvert {

    DynamicDataSourceConvert CONVERT = Mappers.getMapper(DynamicDataSourceConvert.class);

    DynamicDataSource convert(DynamicDataSourceParam in);

    DynamicDataSourceDto convert(DynamicDataSource in);

}
