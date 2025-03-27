package com.zt.eweb.modular.rbac.service.impl;

import com.zt.eweb.modular.rbac.dal.entity.RbacRole;
import com.zt.eweb.modular.rbac.dal.mapper.RbacRoleMapper;
import com.zt.eweb.modular.rbac.service.RbacRoleApplicationService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/15 10:17
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
public class RbacRoleApplicationServiceImpl implements RbacRoleApplicationService {
    @Resource
    private RbacRoleMapper roleMapper;

  @Override
    public  RbacRole  createRoleDefault(RbacRole role) {
        roleMapper.insert(role);
        return role;
    }
}
