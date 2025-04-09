package com.zt.eweb.modular.meta.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.annotation.BigField;
import com.zt.eweb.framework.common.function.EntityBaseFunction;
import com.zt.eweb.framework.mybatis.core.entity.MpBaseEntity;
import com.zt.eweb.framework.mybatis.core.typehandler.JacksonRawTypeHandler;
import com.zt.eweb.modular.meta.client.dto.dataresult.QuerySqlDto;
import com.zt.eweb.modular.meta.client.dto.dataresult.SqlField;
import com.zt.eweb.modular.meta.client.dto.dataresult.SqlParam;
import com.zt.eweb.modular.meta.infra.dal.convert.DataResultSqlConvert;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据集SQL语句
 *
 * @author xxm
 * @since 2023/3/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@DbTable(comment = "数据集SQL语句")
@Accessors(chain = true)
@TableName(value = "base_data_result_sql", autoResultMap = true)
public class DataResultSql extends MpBaseEntity implements EntityBaseFunction<QuerySqlDto> {

    /** 数据源ID */
    private Long databaseId;

    /** 名称 */
    private String name;

    /** 是否集合 */
    private Boolean isList;

    /** sql语句 */
    @BigField
    private String sql;

    /** SQL查询参数 */
    @BigField
    @TableField(typeHandler = JacksonRawTypeHandler.class)
    private List<SqlParam> params;

    /** SQL查询结果字段 */
    @BigField
    @TableField(typeHandler = JacksonRawTypeHandler.class)
    private List<SqlField> fields;

    /**
     * 转换
     */
    @Override
    public QuerySqlDto toDto() {
        return DataResultSqlConvert.CONVERT.convert(this);
    }

}
