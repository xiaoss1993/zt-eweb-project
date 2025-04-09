package com.zt.eweb.modular.meta.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.function.EntityBaseFunction;
import com.zt.eweb.framework.mybatis.core.entity.MpBaseEntity;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryDto;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryParam;
import com.zt.eweb.modular.meta.infra.dal.convert.DictionaryConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典
 *
 * @author xxm
 * @since 2020/11/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_dict")
public class Dictionary extends MpBaseEntity implements EntityBaseFunction<DictionaryDto> {

    /** 名称 */
    private String name;

    /** 分类标签 */
    private String groupTag;

    /** 编码 */
    private String code;

    /** 备注 */
    private String remark;

    /** 是否启用 */
    private Boolean enable;

    public static Dictionary init(DictionaryParam in) {
        return DictionaryConvert.CONVERT.convert(in);
    }

    @Override
    public DictionaryDto toDto() {
        return DictionaryConvert.CONVERT.convert(this);
    }

}
