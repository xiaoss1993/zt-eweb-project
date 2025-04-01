
package com.zt.eweb.modular.binder.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.eweb.framework.mybatis.core.service.impl.BaseServiceImpl;
import com.zt.eweb.framework.mybatis.core.vo.Pagination;
import com.zt.eweb.modular.binder.dto.DepartmentDTO;
import com.zt.eweb.modular.binder.entity.Department;
import com.zt.eweb.modular.binder.mapper.DepartmentMapper;
import com.zt.eweb.modular.binder.service.DepartmentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 部门相关Service实现
 */
@Slf4j
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements
    DepartmentService {

  @Override
  public List<Department> list(Wrapper queryWrapper) {
    return getEntityList(queryWrapper);
  }

  @Override
  public List<Department> getDepartmentSqlList(QueryWrapper<DepartmentDTO> queryWrapper,
      Pagination pagination) {
    return null;
  }

}
