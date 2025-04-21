package com.zt.eweb.rbac.application.impl;

import com.zt.eweb.rbac.application.assembler.PermissionDTOAssembler;
import com.zt.eweb.rbac.client.PermissionApplicationService;
import com.zt.eweb.rbac.client.command.PermissionCommand;
import com.zt.eweb.rbac.domain.model.permission.Permission;
import com.zt.eweb.rbac.domain.model.permission.PermissionId;
import com.zt.eweb.rbac.domain.model.permission.PermissionRepository;
import com.zt.eweb.rbac.domain.service.PermissionDisableService;
import com.zt.eweb.rbac.domain.specification.PermissionCreateSpecification;
import com.zt.eweb.rbac.domain.specification.PermissionDeleteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限应用服务实现类
 *
 * @author haoxin
 * @date 2021-02-17
 **/
@Service
public class PermissionApplicationServiceImpl implements PermissionApplicationService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(PermissionCommand permissionCommand) {
        Permission parent = permissionRepository.find(new PermissionId(permissionCommand.getParentId()));
        Permission permission = PermissionDTOAssembler.toPermission(permissionCommand,parent);
        PermissionCreateSpecification permissionCreateSpecification = new PermissionCreateSpecification(permissionRepository);
        permissionCreateSpecification.isSatisfiedBy(permission);
        permissionRepository.store(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        PermissionId permissionId =  new PermissionId(id);
        PermissionDeleteSpecification permissionDeleteSpecification = new PermissionDeleteSpecification(permissionRepository);
        permissionDeleteSpecification.isSatisfiedBy(permissionId);
        permissionRepository.remove(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(String id) {
        PermissionDisableService permissionDisableService = new PermissionDisableService(permissionRepository);
        permissionDisableService.disable(new PermissionId(id));
    }
}
