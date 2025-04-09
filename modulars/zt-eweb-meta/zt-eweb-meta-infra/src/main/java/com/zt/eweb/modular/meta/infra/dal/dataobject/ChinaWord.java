package com.zt.eweb.modular.meta.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.function.EntityBaseFunction;
import com.zt.eweb.framework.mybatis.core.entity.MpDelEntity;
import com.zt.eweb.modular.meta.client.dto.chinaword.ChinaWordDto;
import com.zt.eweb.modular.meta.client.param.chinaword.ChinaWordParam;
import com.zt.eweb.modular.meta.infra.dal.convert.ChinaWordConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 敏感词
 * @author xxm
 * @since 2023/8/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(value = "base_china_word",autoResultMap = true)
public class ChinaWord extends MpDelEntity implements EntityBaseFunction<ChinaWordDto> {

    /** 敏感词 */

    private String word;
    /** 类型 */
    private String type;
    /** 描述 */
    private String description;
    /** 是否启用 */
    private Boolean enable;
    @Schema(description = "是否是白名单名词")
    private Boolean white;

    /** 创建对象 */
    public static ChinaWord init(ChinaWordParam in) {
        return ChinaWordConvert.CONVERT.convert(in);
    }

    /** 转换成dto */
    @Override
    public ChinaWordDto toDto() {
        return ChinaWordConvert.CONVERT.convert(this);
    }
}
