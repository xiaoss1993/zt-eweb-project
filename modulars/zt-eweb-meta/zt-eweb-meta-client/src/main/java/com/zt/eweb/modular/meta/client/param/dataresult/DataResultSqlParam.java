package com.zt.eweb.modular.meta.client.param.dataresult;

import com.zt.eweb.modular.meta.client.dto.dataresult.SqlField;
import com.zt.eweb.modular.meta.client.dto.dataresult.SqlParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询语句参数类
 *
 * @author xxm
 * @since 2023/3/13
 */
@Data
@Accessors(chain = true)
@Schema(title = "数据集SQL语句参数")
public class DataResultSqlParam {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "数据源ID")
    private Long databaseId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "sql语句")
    private String sql;

    @Schema(description = "是否集合")
    private Boolean isList;

    @Schema(description = "SQL查询参数")
    private List<SqlParam> params;

    @Schema(description = "SQL查询结果字段")
    private List<SqlField> fields;

}
