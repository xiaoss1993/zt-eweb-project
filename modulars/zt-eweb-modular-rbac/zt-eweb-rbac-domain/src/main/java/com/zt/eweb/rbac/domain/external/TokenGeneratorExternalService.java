package com.zt.eweb.rbac.domain.external;

/**
 * 生成Token外部接口
 *
 * 
 * @date 2021-04-23
 **/
public interface TokenGeneratorExternalService {

    /**
     * 生成token
     *
     * @return
     */
    String generateValue();
}
