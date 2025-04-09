package com.zt.eweb.framework.common.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * kv键值对象
 *
 * 
 * @since 2021/5/18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Schema(title = "kv键值对象")
public class KeyValue implements Serializable {

    private static final long serialVersionUID = 3427649251589010105L;

    @Schema(description = "键")
    private String key;

    @Schema(description = "值")
    private String value;

}
