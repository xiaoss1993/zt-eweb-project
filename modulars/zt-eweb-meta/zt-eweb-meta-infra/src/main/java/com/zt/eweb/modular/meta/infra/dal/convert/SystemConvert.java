package com.zt.eweb.modular.meta.infra.dal.convert;

import com.zt.eweb.modular.meta.client.dto.parameter.SystemParameterDto;
import com.zt.eweb.modular.meta.client.param.system.SystemParameterParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.SystemParameter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统参数和系统配置实体类转换
 *
 * @author xxm
 * @since 2021/10/25
 */
@Mapper
public interface SystemConvert {

    SystemConvert CONVERT = Mappers.getMapper(SystemConvert.class);

    SystemParameterDto convert(SystemParameter in);

    SystemParameter convert(SystemParameterParam in);

}
