package com.zt.eweb.modular.meta.client.dto.dataresult;

import lombok.Getter;
import lombok.Setter;

/**
 * SQL查询参数
 */
@Getter
@Setter
public class SqlParam {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 类型
     *
     * @see
     */
    private String type;

}
