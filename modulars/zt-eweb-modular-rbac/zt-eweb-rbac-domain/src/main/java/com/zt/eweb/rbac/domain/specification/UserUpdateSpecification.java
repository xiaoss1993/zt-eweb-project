package com.zt.eweb.rbac.domain.specification;

import com.zt.eweb.framework.common.domain.AbstractSpecification;
import com.zt.eweb.rbac.domain.model.tenant.Tenant;
import com.zt.eweb.rbac.domain.model.user.User;
import com.zt.eweb.rbac.domain.model.tenant.TenantRepository;

/**
 * 用户修改Specification
 *
 * @author haoxin
 * @date 2021-02-27
 **/
public class UserUpdateSpecification extends AbstractSpecification<User> {

    private TenantRepository tenantRepository;

    public UserUpdateSpecification(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public boolean isSatisfiedBy(User user) {
        Tenant tenant = tenantRepository.find(user.getTenantId());
        if(tenant.getCreatorId().sameValueAs(user.getUserId())) {
            throw new RuntimeException("租户创建者无法修改");
        }
        return false;
    }
}
