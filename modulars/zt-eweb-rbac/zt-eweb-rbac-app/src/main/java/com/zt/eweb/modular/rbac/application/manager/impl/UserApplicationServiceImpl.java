package com.zt.eweb.modular.rbac.application.manager.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.eweb.framework.cache.IEasyCache;
import com.zt.eweb.framework.common.base.result.Response;
import com.zt.eweb.framework.common.constant.EasyAdminConstants;
import com.zt.eweb.modular.rbac.client.dto.LoginDto;
import com.zt.eweb.modular.rbac.client.dto.UserDataPower;
import com.zt.eweb.modular.rbac.client.dto.UserInfoAndPowers;
import com.zt.eweb.modular.rbac.client.manager.UserApplicationService;
import com.zt.eweb.modular.rbac.client.manager.UserQueryService;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserDO;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户应用服务实现类
 *
 * @author haoxin
 * @date 2021-02-09
 **/
@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    @Autowired
    private IEasyCache iEasyCache;
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserQueryService userQueryService;

    @Override
    public Response login(LoginDto loginDto) {
        // 验证码是否正确
        String code = iEasyCache.get(loginDto.getUid());
        iEasyCache.remove(loginDto.getUid());
        if (!StrUtil.equalsIgnoreCase(code, loginDto.getCaptchaCode())) {
            return Response.error500("验证码不正确或已失效");
        }
        // 单机版：在map中创建了会话，token id等映射关系 // 写入cookie
        SysUserDO sysUser = userMapper.selectOne(Wrappers.<SysUserDO>lambdaQuery()
                .eq(SysUserDO::getUserName, loginDto.getUsername())
                .eq(SysUserDO::getPassword, SecureUtil.sha256(loginDto.getPassword())));

        if (sysUser == null) {
            return Response.error500("用户名或密码不正确");
        }
        if (sysUser.getEnable() == 0) {
            return Response.error500("用户已被禁用");
        }
        StpUtil.login(sysUser.getUserId());
        // 获取用户的数据权限
        List<UserDataPower> userDataPowers = userQueryService.getUserDataPowers(sysUser.getUserId());

        UserInfoAndPowers.UserInfoAndPowersBuilder userInfoAndPowersBuilder = UserInfoAndPowers.builder()
                .deptId(sysUser.getDeptId())
                .userId(sysUser.getUserId())
                .nickName(sysUser.getNickName())
                .userDataPowers(userDataPowers);
        StpUtil.getSession().set(EasyAdminConstants.CURRENT_USER, userInfoAndPowersBuilder.build());
        return Response.ok(StpUtil.getTokenInfo());
    }
}
