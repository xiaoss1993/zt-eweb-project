package com.zt.eweb.modular.meta.infra.dal.convert;

import com.zt.eweb.modular.meta.client.dto.dataresult.QuerySqlDto;
import com.zt.eweb.modular.meta.client.param.dataresult.DataResultSqlParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.DataResultSql;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xxm
 * @since 2023/3/13
 */
@Mapper
public interface DataResultSqlConvert {

    DataResultSqlConvert CONVERT = Mappers.getMapper(DataResultSqlConvert.class);

    QuerySqlDto convert(DataResultSql in);

    DataResultSql convert(DataResultSqlParam in);

}
