package com.zt.eweb.modular.meta.infra.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zt.eweb.framework.common.annotation.EncryptionField;
import com.zt.eweb.framework.common.function.EntityBaseFunction;
import com.zt.eweb.framework.mybatis.core.entity.MpBaseEntity;
import com.zt.eweb.modular.meta.client.dto.dynamicsource.DynamicDataSourceDto;
import com.zt.eweb.modular.meta.client.param.dynamicsource.DynamicDataSourceParam;
import com.zt.eweb.modular.meta.infra.dal.convert.DynamicDataSourceConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 动态数据源管理
 *
 * @author xxm
 * @since 2022-09-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("base_dynamic_data_source")
public class DynamicDataSource extends MpBaseEntity implements
    EntityBaseFunction<DynamicDataSourceDto> {

    /** 数据源编码 */
    private String code;

    /** 数据源名称 */
    private String name;

    /** 数据库类型 */
    private String databaseType;

    /** 是否启动自动加载 */
    private boolean autoLoad;

    /** 驱动类 */
    private String dbDriver;

    /** 数据库地址 */
    private String dbUrl;

    /** 用户名 */
    private String dbUsername;

    /** 密码 */
    @EncryptionField
    private String dbPassword;

    /** 备注 */
    private String remark;

    /** 创建对象 */
    public static DynamicDataSource init(DynamicDataSourceParam in) {
        return DynamicDataSourceConvert.CONVERT.convert(in);
    }

    /** 转换成dto */
    @Override
    public DynamicDataSourceDto toDto() {
        return DynamicDataSourceConvert.CONVERT.convert(this);
    }

}
