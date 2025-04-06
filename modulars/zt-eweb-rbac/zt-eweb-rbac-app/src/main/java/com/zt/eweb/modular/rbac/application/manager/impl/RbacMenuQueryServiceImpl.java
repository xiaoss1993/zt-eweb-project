package com.zt.eweb.modular.rbac.application.manager.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.eweb.modular.rbac.client.dto.MenuVo;
import com.zt.eweb.modular.rbac.client.manager.RbacMenuQueryService;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysPower;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysRolePower;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserRoleDO;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysMenuMapper;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysRolePowerMapper;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RbacMenuQueryServiceImpl implements RbacMenuQueryService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRolePowerMapper rolePowerMapper;

    @Override
    public List<MenuVo> menu() {
        Long loginId = StpUtil.getLoginIdAsLong();

        List<SysPower> sysPowers = null;
        // 超级管理员开玩笑
        if (loginId.longValue() == 1L) {
            sysPowers = menuMapper.findAllByStatusOrderBySort(true);
        } else {
            sysPowers = getSysMenusPowerByLoginUser(loginId);
        }
        List<MenuVo> menuInfo = new ArrayList<>();
        for (SysPower e : sysPowers) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getMenuId());
            menuVO.setPid(e.getPid());
            menuVO.setHref(e.getHref());
            menuVO.setTitle(e.getTitle());
            menuVO.setIcon(e.getIcon());
            menuVO.setOpenType(e.getOpenType());
            menuVO.setType(e.getType());
            menuVO.setPowerCode(e.getPowerCode());
            menuInfo.add(menuVO);
        }

        return toTree(menuInfo, 0L);
    }

    public static List<MenuVo> toTree(List<MenuVo> treeList, Long pid) {
        List<MenuVo> retList = new ArrayList<>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(child, treeList));
            }
        }
        return parent;
    }

    private List<SysPower> getSysMenusPowerByLoginUser(Long loginId) {
        List<SysUserRoleDO> userRoleDOList = userRoleMapper.selectList(Wrappers.<SysUserRoleDO>lambdaQuery().eq(SysUserRoleDO::getUserId, loginId));

        List<Long> roleIds = userRoleDOList.stream().map(sysUserRole -> Long.valueOf(sysUserRole.getRoleId())).collect(Collectors.toList());
        List<SysRolePower> rolePowerLists = rolePowerMapper.selectList(Wrappers.<SysRolePower>lambdaQuery().in(SysRolePower::getRoleId, roleIds));
        List<Long> powerIds = rolePowerLists.stream().map(sysRolePower -> sysRolePower.getPowerId()).collect(Collectors.toList());
        return menuMapper.selectList(Wrappers.<SysPower>lambdaQuery().in(SysPower::getMenuId, powerIds).eq(SysPower::getEnable, true).orderByAsc(SysPower::getSort));
    }
}
