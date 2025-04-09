package com.zt.eweb.modular.meta.infra.dal.convert;

import com.zt.eweb.modular.meta.client.dto.dict.DictionaryDto;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryItemDto;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryItemSimpleDto;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryItemParam;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.Dictionary;
import com.zt.eweb.modular.meta.infra.dal.dataobject.DictionaryItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 渠道转换
 *
 * @author xxm
 * @since 2021/7/6
 */
@Mapper
public interface DictionaryConvert {

    DictionaryConvert CONVERT = Mappers.getMapper(DictionaryConvert.class);

    Dictionary convert(DictionaryParam in);

    DictionaryDto convert(Dictionary in);

    DictionaryItem convert(DictionaryItemParam in);

    DictionaryItemDto convert(DictionaryItem in);

    DictionaryItemSimpleDto convertSimple(DictionaryItem in);

}
