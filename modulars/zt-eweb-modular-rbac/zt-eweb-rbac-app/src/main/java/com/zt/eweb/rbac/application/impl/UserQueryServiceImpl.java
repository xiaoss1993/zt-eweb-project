package com.zt.eweb.rbac.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.framework.common.domain.StatusEnum;
import com.zt.eweb.framework.common.utils.Page;
import com.zt.eweb.framework.common.utils.PageAssembler;
import com.zt.eweb.framework.common.utils.mybatis.Query;
import com.zt.eweb.rbac.application.assembler.UserDTOAssembler;
import com.zt.eweb.rbac.client.UserQueryService;
import com.zt.eweb.rbac.client.dto.TenantDTO;
import com.zt.eweb.rbac.client.dto.UserDTO;
import com.zt.eweb.rbac.domain.model.user.Token;
import com.zt.eweb.rbac.domain.model.user.User;
import com.zt.eweb.rbac.domain.model.user.UserId;
import com.zt.eweb.rbac.domain.model.user.UserRepository;
import com.zt.eweb.rbac.domain.specification.LoginByTokenSpecification;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysPermissionDO;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysTenantDO;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysUserDO;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysPermissionMapper;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysTenantMapper;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysUserDO> page = sysUserMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }

    @Override
    public UserDTO find(String userId) {
        return UserDTOAssembler.fromUser(userRepository.find(new UserId(userId)));
    }

    @Override
    public UserDTO queryByToken(String token) {
        Token accountToken = new Token(token,null);
        User user = userRepository.find(accountToken);
        LoginByTokenSpecification loginByTokenSpecification = new LoginByTokenSpecification();
        loginByTokenSpecification.isSatisfiedBy(user);
        UserDTO userDTO = UserDTOAssembler.fromUser(user);
        SysTenantDO tenantDO = sysTenantMapper.selectById(user.getTenantId());
        userDTO.setTenantName(tenantDO.getTenantName());
        List<SysPermissionDO> sysPermissionDOList;
        if(user.getUserId().isSysAdmin()) {
            sysPermissionDOList = sysPermissionMapper.selectList(new QueryWrapper<SysPermissionDO>().eq("status", StatusEnum.ENABLE.getValue()));
        } else {
            sysPermissionDOList = sysPermissionMapper.queryPermissionByUserId(user.getUserId().getId());
        }
        Set<String> permissionIds = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        for(SysPermissionDO sysPermissionDO : sysPermissionDOList){
            permissionIds.add(sysPermissionDO.getId());
            if(sysPermissionDO.getPermissionCodes() != null){
                permsSet.addAll(Arrays.asList(sysPermissionDO.getPermissionCodes().trim().split(",")));
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("token",token);
        List<SysUserDO> sysUserDOList =  sysUserMapper.queryUserNoTenant(params);
        List<TenantDTO> tenants = new ArrayList<>();
        for(SysUserDO sysUserDO : sysUserDOList) {
            TenantDTO tenantDTO = new TenantDTO();
            tenantDTO.setTenantId(sysUserDO.getTenantId());
            tenantDTO.setTenantCode(sysUserDO.getTenantCode());
            tenantDTO.setTenantName(sysUserDO.getTenantName());
            tenants.add(tenantDTO);
        }
        userDTO.setPermissionCodes(permsSet);
        userDTO.setPermissionIds(permissionIds);
        userDTO.setTenants(tenants);
        return userDTO;
    }
}
