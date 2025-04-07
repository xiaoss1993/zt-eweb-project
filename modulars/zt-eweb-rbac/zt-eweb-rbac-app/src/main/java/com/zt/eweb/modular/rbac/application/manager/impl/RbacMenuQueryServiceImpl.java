package com.zt.eweb.modular.rbac.application.manager.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.eweb.modular.rbac.client.dto.MenuVo;
import com.zt.eweb.modular.rbac.client.manager.RbacMenuQueryService;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysPermissionDO;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysPermissionMapper;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysRolePermissionMapper;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RbacMenuQueryServiceImpl implements RbacMenuQueryService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRolePermissionMapper  rolePermissionMapper;

    @Override
    public List<MenuVo> menu() {
        Long loginId = StpUtil.getLoginIdAsLong();

        List<SysPermissionDO> permissionDOList = null;
        // 超级管理员开玩笑
        if (loginId.longValue() == 1L) {
            permissionDOList = permissionMapper.selectList(Wrappers.query());
        } else {
            permissionDOList = permissionMapper.selectList(Wrappers.query());
        }
        List<MenuVo> menuInfo = new ArrayList<>();

        for (SysPermissionDO e : permissionDOList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getId());
            menuVO.setPid(e.getParentId());
            menuVO.setHref(e.getMenuUrl());
            menuVO.setTitle(e.getPermissionName());
            menuVO.setIcon(e.getMenuIcon());
            menuVO.setOpenType(e.getPermissionType());
            menuVO.setType(e.getPermissionType());
            menuVO.setPowerCode(e.getPermissionCodes());
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

}
