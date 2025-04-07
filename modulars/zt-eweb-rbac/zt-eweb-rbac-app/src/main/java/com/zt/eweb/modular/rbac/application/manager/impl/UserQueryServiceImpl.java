package com.zt.eweb.modular.rbac.application.manager.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.zt.eweb.framework.mybatis.core.util.BeanUtils;
import com.zt.eweb.modular.rbac.client.dto.RbacUserDto;
import com.zt.eweb.modular.rbac.client.dto.SysUserDTO;
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
    public List<UserDataPower> getUserDataPowers(String userId) {
        return null;
    }

    @Override
    public String getById(String loginId) {
        SysUserDO userDO = userMapper.selectById(loginId);
        if (userDO != null) {
            userDO.getUserName();
        }
        return null;
    }

    @Override
    public List<String> getPowerCodesByUserIdAndPowerType(Long userId, Integer roleType) {
        return null;
    }

    @Override
    public List<String> getRoleCodesByUserIdAndRoleType(Long userId, Integer roleType) {
        return null;
    }

    @Override
    public RbacUserDto getUserInfoById(long loginIdAsLong) {
        SysUserDO sysUserDO = userMapper.selectById(loginIdAsLong);
        String userDOId = sysUserDO.getId();

        RbacUserDto userDto = new RbacUserDto();
        BeanUtils.copyProperties(sysUserDO, userDto);
        return userDto;
    }

    @Override
    public Page<SysUserDTO> page(Page<SysUserDTO> roadPage, QueryWrapper<SysUserDTO> queryWrapper) {
        QueryWrapper<SysUserDO> queryWrapperDO = new QueryWrapper<>();
        BeanUtil.copyProperties(queryWrapper, queryWrapperDO);
        PageHelper.startPage((int) roadPage.getCurrent(), (int) roadPage.getSize());
        List<SysUserDO> userDOList = userMapper.selectList(Wrappers.emptyWrapper());
        List<SysUserDTO> userDTOList = BeanUtils.convertList(userDOList, SysUserDTO.class);
        Page<SysUserDTO> userDOPage = new Page<>(roadPage.getCurrent(), roadPage.getSize());
        userDOPage.setRecords(userDTOList);
        return userDOPage;
    }

}
