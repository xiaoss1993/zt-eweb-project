package com.zt.eweb.modular.meta.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.function.EntityBaseFunction;
import com.zt.eweb.framework.mybatis.core.entity.MpBaseEntity;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryItemDto;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryItemSimpleDto;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryItemParam;
import com.zt.eweb.modular.meta.infra.dal.convert.DictionaryConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 字典项
 *
 * @author xxm
 * @since 2020/4/15 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("base_dict_item")
public class DictionaryItem extends MpBaseEntity implements EntityBaseFunction<DictionaryItemDto> {

    /** 字典ID */
    private Long dictId;

    /** 字典编码 */
    private String dictCode;

    /** 字典项编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 字典项排序 */
    private Double sortNo;

    /** 是否启用 */
    private Boolean enable;

    /** 备注 */
    private String remark;

    public static DictionaryItem init(DictionaryItemParam in) {
        return DictionaryConvert.CONVERT.convert(in);
    }

    @Override
    public DictionaryItemDto toDto() {
        return DictionaryConvert.CONVERT.convert(this);
    }

    /**
     * 转换成简单对象
     */
    public DictionaryItemSimpleDto toSimpleDto() {
        return DictionaryConvert.CONVERT.convertSimple(this);

    }

}
