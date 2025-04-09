package com.zt.eweb.modular.meta.infra.dal.convert;


import com.zt.eweb.modular.meta.client.dto.dynamicform.DynamicFormDto;
import com.zt.eweb.modular.meta.client.param.dynamicform.DynamicFormParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.DynamicForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 动态表单
 *
 */
@Mapper
public interface DynamicFormConvert {

    DynamicFormConvert CONVERT = Mappers.getMapper(DynamicFormConvert.class);

    DynamicForm convert(DynamicFormParam in);

    DynamicFormDto convert(DynamicForm in);

}
