package com.zt.eweb.rbac.client;

/**
 * 租户应用服务接口
 *
 * @author haoxin
 * @date 2021-02-14
 **/
public interface TenantApplicationService {

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);
}
