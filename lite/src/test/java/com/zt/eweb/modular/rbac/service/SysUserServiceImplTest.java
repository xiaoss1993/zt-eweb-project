//package com.zt.eweb.modular.rbac.service;
//
//import com.zt.eweb.modular.rbac.application.manager.impl.UserQueryServiceImpl;
//import com.zt.eweb.modular.rbac.client.dto.UserDataPower;
//import com.zt.eweb.modular.rbac.client.manager.UserQueryService;
//import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserDO;
//import com.zt.eweb.modular.rbac.infra.dal.mapper.SysUserMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.api.support.membermodification.MemberModifier;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.powermock.api.mockito.PowerMockito.when;
//
//@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
//@PrepareForTest({UserQueryService.class}) // 所有需要测试的类列在此处
//@Slf4j
//public class SysUserServiceImplTest {
//
//    /**
//     * 需要单元测试的类
//     */
//    @InjectMocks
//    UserQueryServiceImpl queryService = new UserQueryServiceImpl();
//    /**
//     * 单元测试类依赖的类
//     */
//    @Mock
//    private SysUserMapper userMapper;
//
//    @Test
//    public void getUserDataPowers() throws IllegalAccessException {
//        /** 录制 mock操作 */
//        List<UserDataPower> result = new ArrayList<>();
//        UserQueryServiceImpl spy = PowerMockito.spy(queryService);
//
//        when(userMapper.getUserDataPowers(4L)).thenReturn(result);
//        /**
//         * 模拟私有属性
//         */
//        MemberModifier
//                .field(UserQueryServiceImpl.class, "baseMapper").set(
//                        queryService, userMapper);
//
//        List<UserDataPower> userDataPowers = queryService.getUserDataPowers(4L);
//        Assert.assertEquals(1L, userDataPowers.size());
//    }
//}
