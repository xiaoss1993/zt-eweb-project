
package com.zt.eweb.modular.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zt.eweb.framework.mybatis.core.binding.Binder;
import com.zt.eweb.modular.binder.entity.Department;
import com.zt.eweb.modular.binder.service.DepartmentService;
import com.zt.eweb.modular.binder.vo.DepartmentVO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试字段绑定
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2019/06/22
 */
@RunWith(SpringRunner.class)
@Slf4j
@DisplayName("DeepBinderTest")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeepBinderTest {

  @Autowired
  DepartmentService departmentService;

  @Test
  public void testEntityListDeepBinder() {
    // 加载测试数据
    LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Department::getParentId, "0");
    List<Department> entityList = departmentService.list(queryWrapper);
    List<DepartmentVO> voList = Binder.convertAndBindRelations(entityList, DepartmentVO.class);

  }

}
