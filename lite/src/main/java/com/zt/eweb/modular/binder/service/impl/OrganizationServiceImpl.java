
package com.zt.eweb.modular.binder.service.impl;

import com.zt.eweb.framework.mybatis.core.service.impl.BaseServiceImpl;
import com.zt.eweb.modular.binder.entity.Organization;
import com.zt.eweb.modular.binder.mapper.OrganizationMapper;
import com.zt.eweb.modular.binder.service.OrganizationService;
import org.springframework.stereotype.Service;

/**
 * 单位相关Service实现
 *
 * @author mazc@dibo.ltd
 * @version 2018/12/23
 */
@Service
public class OrganizationServiceImpl extends
    BaseServiceImpl<OrganizationMapper, Organization> implements
    OrganizationService {

}
