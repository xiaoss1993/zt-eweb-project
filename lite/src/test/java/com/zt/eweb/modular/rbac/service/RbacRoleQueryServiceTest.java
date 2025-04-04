package com.zt.eweb.modular.rbac.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.eweb.modular.rbac.dal.entity.RbacRole;
import com.zt.eweb.modular.rbac.dal.mapper.RbacRoleMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/16 10:42
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/16      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Slf4j
@DisplayName("角色管理服务测试")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RbacRoleQueryServiceTest {
    @Autowired
    private RbacRoleQueryService    rbacRoleQueryService;
    @Autowired
    private RbacRoleMapper      roleMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("分页查询角色列表")
    void testQueryRolePage() {
        List<RbacRole> roleList = roleMapper.selectList(Wrappers.emptyWrapper());
    }
    @Test
    public void testInt(){
        int a =1000,b=1000;
        System.out.println(a==b);
        Integer  c = 1000;
        Integer  d = 1000;
        System.out.println(c.intValue() == d.intValue());
    }
}

