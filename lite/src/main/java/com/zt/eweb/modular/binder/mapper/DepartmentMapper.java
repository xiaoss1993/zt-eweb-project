package com.zt.eweb.modular.binder.mapper;

import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.modular.binder.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门Mapper
 *
 * @author mazc@dibo.ltd
 * @version 2018/12/22
 */
@Mapper
public interface DepartmentMapper extends BaseCrudMapper<Department> {

}