package com.zt.eweb.modular.rbac.service;

import com.wujiuye.flow.FlowHelper;
import com.wujiuye.flow.FlowType;
import com.wujiuye.flow.Flower;
import com.zt.eweb.modular.rbac.dal.mapper.RbacRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.anyline.service.AnylineService;
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

    @Autowired
    protected AnylineService service;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("分页查询角色列表")
    void testQueryRolePage() {

    }
    private FlowHelper flowHelper = new FlowHelper(FlowType.Hour);

    @Test
    void countQPS(){
        Flower flower = flowHelper.getFlow(FlowType.Hour);
        System.out.println("总请求数:"+flower.total());
        System.out.println("成功请求数:"+flower.totalSuccess());
        System.out.println("异常请求数:"+flower.totalException());
        System.out.println("平均请求耗时:"+flower.avgRt());
        System.out.println("最大请求耗时:"+flower.maxRt());
        System.out.println("最小请求耗时:"+flower.minRt());
        System.out.println("平均请求成功数(每毫秒):"+flower.successAvg());
        System.out.println("平均请求异常数(每毫秒):"+flower.exceptionAvg());
        System.out.println();
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

