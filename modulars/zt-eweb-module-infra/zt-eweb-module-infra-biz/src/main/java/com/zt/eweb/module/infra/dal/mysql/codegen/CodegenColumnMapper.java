package com.zt.eweb.module.infra.dal.mysql.codegen;

import com.zt.eweb.framework.mybatis.core.mapper.BaseMapperX;
import com.zt.eweb.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zt.eweb.module.infra.dal.dataobject.codegen.CodegenColumnDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodegenColumnMapper extends BaseMapperX<CodegenColumnDO> {

    default List<CodegenColumnDO> selectListByTableId(Long tableId) {
        return selectList(new LambdaQueryWrapperX<CodegenColumnDO>()
                .eq(CodegenColumnDO::getTableId, tableId)
                .orderByAsc(CodegenColumnDO::getOrdinalPosition));
    }

    default void deleteListByTableId(Long tableId) {
        delete(new LambdaQueryWrapperX<CodegenColumnDO>()
                .eq(CodegenColumnDO::getTableId, tableId));
    }

}
