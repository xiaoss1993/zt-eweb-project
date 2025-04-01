package com.zt.eweb.modular.binder.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.eweb.framework.mybatis.core.service.BaseService;
import com.zt.eweb.framework.mybatis.core.vo.Pagination;
import com.zt.eweb.modular.binder.dto.DepartmentDTO;
import com.zt.eweb.modular.binder.entity.Department;
import java.util.List;

/**
 * 部门相关Service
 */
public interface DepartmentService extends BaseService<Department> {

  /**
   * 获取指定条件的Entity集合
   *
   * @param queryWrapper
   * @return
   * @throws Exception
   */
  List<Department> list(Wrapper queryWrapper);

  List<Department> getDepartmentSqlList(QueryWrapper<DepartmentDTO> queryWrapper,
      Pagination pagination);
}
