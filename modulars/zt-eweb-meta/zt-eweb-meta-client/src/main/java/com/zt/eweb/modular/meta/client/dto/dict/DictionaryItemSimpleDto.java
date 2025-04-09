package com.zt.eweb.modular.meta.client.dto.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据字典项(简单)
 *
 * @author xxm
 * @since 2020/4/15 17:55
 */
@Data
@Accessors(chain = true)
@Schema(title = "数据字典项Dto")
public class DictionaryItemSimpleDto implements Serializable {

    private static final long serialVersionUID = 7403674221398097611L;

    @Schema(description = "字典编码")
    private String dictCode;

    @Schema(description = "字典项编码")
    private String code;

    @Schema(description = "名称")
    private String name;

}
