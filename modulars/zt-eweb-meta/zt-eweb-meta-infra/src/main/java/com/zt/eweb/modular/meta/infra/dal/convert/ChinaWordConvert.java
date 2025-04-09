package com.zt.eweb.modular.meta.infra.dal.convert;


import com.zt.eweb.modular.meta.client.dto.chinaword.ChinaWordDto;
import com.zt.eweb.modular.meta.client.param.chinaword.ChinaWordParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.ChinaWord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 敏感词
 * @author xxm
 * @since 2023-08-09
 */
@Mapper
public interface ChinaWordConvert {
    ChinaWordConvert CONVERT = Mappers.getMapper(ChinaWordConvert.class);

    ChinaWord convert(ChinaWordParam in);

    ChinaWordDto convert(ChinaWord in);

}
