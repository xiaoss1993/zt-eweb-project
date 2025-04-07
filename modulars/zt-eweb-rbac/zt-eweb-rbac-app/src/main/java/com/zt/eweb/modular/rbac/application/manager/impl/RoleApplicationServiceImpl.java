package com.zt.eweb.modular.rbac.application.manager.impl;

import com.zt.eweb.modular.rbac.client.command.RoleCommand;
import com.zt.eweb.modular.rbac.client.manager.RoleApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色应用服务实现类
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@Service
public class RoleApplicationServiceImpl implements RoleApplicationService {

    @Override
    public void saveOrUpdate(RoleCommand roleCommand) {

    }

    @Override
    public void deleteBatch(List<String> list) {

    }

    @Override
    public void disable(String id) {

    }
}
