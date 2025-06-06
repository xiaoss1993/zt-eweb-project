package com.zt.eweb.framework.common.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 基础Dto
 *
 * 
 * @since 2020/6/1 16:12
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseDto implements Serializable {

    private static final long serialVersionUID = 2985535678913619503L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后修改时间")
    private LocalDateTime lastModifiedTime;

    @Schema(description = "版本号")
    private Integer version;

}
