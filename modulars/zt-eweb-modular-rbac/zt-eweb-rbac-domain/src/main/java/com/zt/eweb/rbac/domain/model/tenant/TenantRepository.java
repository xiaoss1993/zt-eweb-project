package com.zt.eweb.rbac.domain.model.tenant;

/**
 * 租户-Repository接口
 *
 *
 * @date 2021-02-14
 **/
public interface TenantRepository {

    /**
     * 通过租户id获取租户
     *
     * @param tenantId
     * @return
     */
    Tenant find(TenantId tenantId);

    /**
     * 通过租户名称获取租户
     *
     * @param tenantName
     * @return
     */
    Tenant find(TenantName tenantName);

    /**
     * 通过租户编码获取租户
     *
     * @param tenantCode
     * @return
     */
    Tenant find(TenantCode tenantCode);

    /**
     * 保存
     *
     * @param tenant
     */
    TenantId store(Tenant tenant);

}
