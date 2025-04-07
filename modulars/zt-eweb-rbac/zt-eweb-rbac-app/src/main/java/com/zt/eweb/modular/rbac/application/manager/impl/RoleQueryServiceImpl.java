package com.zt.eweb.modular.rbac.application.manager.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.eweb.modular.rbac.client.dto.RoleDTO;
import com.zt.eweb.modular.rbac.client.manager.RoleQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    @Override
    public Page queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<RoleDTO> listAll() {
        return null;
    }

    @Override
    public RoleDTO getById(String id) {
        return null;
    }
}
