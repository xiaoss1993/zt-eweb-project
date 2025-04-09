package com.zt.eweb.modular.meta.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.annotation.BigField;
import com.zt.eweb.framework.common.function.EntityBaseFunction;
import com.zt.eweb.framework.mybatis.core.entity.MpBaseEntity;
import com.zt.eweb.modular.meta.client.dto.dynamicform.DynamicFormDto;
import com.zt.eweb.modular.meta.client.param.dynamicform.DynamicFormParam;
import com.zt.eweb.modular.meta.infra.dal.convert.DynamicFormConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 动态表单
 *
 * @author xxm
 * @since 2022-07-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(value = "base_dynamic_form", autoResultMap = true)
public class DynamicForm extends MpBaseEntity implements EntityBaseFunction<DynamicFormDto> {

    /** 表单名称 */
    private String name;

    /** 表单键名 */
    private String code;

    /** 表单内容 */
    @BigField
    private String value;

    /** 备注 */
    private String remark;

    /** 创建对象 */
    public static DynamicForm init(DynamicFormParam in) {
        return DynamicFormConvert.CONVERT.convert(in);
    }

    /** 转换成dto */
    @Override
    public DynamicFormDto toDto() {
        return DynamicFormConvert.CONVERT.convert(this);
    }

}
