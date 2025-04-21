package com.zt.eweb.rbac.application.impl;

import com.zt.eweb.rbac.client.TenantApplicationService;
import com.zt.eweb.rbac.domain.model.tenant.Tenant;
import com.zt.eweb.rbac.domain.model.tenant.TenantId;
import com.zt.eweb.rbac.domain.model.tenant.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户应用服务实现类
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Service
public class TenantApplicationServiceImpl implements TenantApplicationService {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(String id) {
        TenantId tenantId = new TenantId(id);
        if(tenantId.isPlatformId()) {
            throw new RuntimeException("平台租户无法删除");
        }
        Tenant tenant = tenantRepository.find(tenantId);
        tenant.disable();
        tenantRepository.store(tenant);
    }

}
