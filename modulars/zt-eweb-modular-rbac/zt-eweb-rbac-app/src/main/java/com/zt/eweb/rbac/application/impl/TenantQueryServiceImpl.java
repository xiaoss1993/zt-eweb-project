package com.zt.eweb.rbac.application.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.framework.common.utils.Page;
import com.zt.eweb.framework.common.utils.PageAssembler;
import com.zt.eweb.framework.common.utils.mybatis.Query;
import com.zt.eweb.rbac.client.TenantQueryService;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysTenantDO;
import com.zt.eweb.rbac.infrastructure.persistence.mapper.SysTenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 租户查询服务实现类
 *
 * @author haoxin
 * @date 2021-05-10
 **/
@Service
public class TenantQueryServiceImpl implements TenantQueryService {

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysTenantDO> page = sysTenantMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }
}
