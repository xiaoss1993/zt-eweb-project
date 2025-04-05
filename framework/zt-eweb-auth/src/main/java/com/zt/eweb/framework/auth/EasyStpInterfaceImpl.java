package com.zt.eweb.framework.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.zt.eweb.modular.rbac.client.manager.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class EasyStpInterfaceImpl implements StpInterface {
    @Autowired
    private UserQueryService userQueryService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        // 一个优化点事这里可以放进缓存，在用户登出时清除缓存
        return userQueryService.getPowerCodesByUserIdAndPowerType(Long.valueOf((String) loginId), 2);
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        // 一个优化点事这里可以放进缓存，在用户登出时清除缓存
        return userQueryService.getRoleCodesByUserIdAndRoleType(Long.valueOf((String) loginId), null);
    }
}
