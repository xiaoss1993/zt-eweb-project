package com.zt.eweb.modular.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.eweb.modular.rbac.dal.entity.RbacRole;
import com.zt.eweb.modular.rbac.dal.mapper.RbacRoleMapper;
import com.zt.eweb.modular.rbac.dal.query.RbacRoleQuery;
import com.zt.eweb.modular.rbac.service.RbacRoleQueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/15 10:16
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/15      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class RbacRoleQueryServiceImpl implements RbacRoleQueryService {
    @Resource
    private RbacRoleMapper  roleMapper;
    @Override
    public List<RbacRole> queryRolePage(RbacRoleQuery roleQuery) {
        QueryWrapper<RbacRole> query = Wrappers.query();
        query.lambda().eq(RbacRole::getCode,"admin");
        return roleMapper.selectList(query);
    }
}
