package com.zt.eweb.modular.rbac.application.manager.impl;

import com.zt.eweb.modular.rbac.client.dto.UserDataPower;
import com.zt.eweb.modular.rbac.client.manager.UserQueryService;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserDO;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<UserDataPower> getUserDataPowers(Long userId) {
        return userMapper.getUserDataPowers(userId);
    }

    @Override
    public String getById(Long loginId) {
        SysUserDO userDO = userMapper.selectById(loginId);
        if (userDO != null) {
            userDO.getNickName();
        }
        return null;
    }

    @Override
    public List<String> getPowerCodesByUserIdAndPowerType(Long userId, Integer roleType) {
        return userMapper.getPowerCodesByUserIdAndPowerType(userId, roleType);
    }

    @Override
    public List<String> getRoleCodesByUserIdAndRoleType(Long userId, Integer roleType) {
        return userMapper.getRoleCodesByUserIdAndRoleType(userId, roleType);
    }

}
