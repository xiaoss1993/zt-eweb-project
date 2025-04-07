package com.zt.eweb.modular.rbac.application.manager.impl;

import com.zt.eweb.modular.rbac.client.dto.PermissionDTO;
import com.zt.eweb.modular.rbac.client.manager.PermissionQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class PermissionQueryServiceImpl implements PermissionQueryService {

    @Override
    public List<PermissionDTO> listAllMenu() {
        return null;
    }

    @Override
    public PermissionDTO getById(String id) {
        return null;
    }
}
