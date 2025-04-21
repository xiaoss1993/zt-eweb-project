package com.zt.eweb.rbac.adapter.web.controller.rbac;


import com.zt.eweb.framework.common.utils.CommonConstant;
import com.zt.eweb.framework.common.utils.Page;
import com.zt.eweb.rbac.adapter.web.common.AbstractController;
import com.zt.eweb.rbac.adapter.web.common.Result;
import com.zt.eweb.rbac.adapter.web.util.log.SysLog;
import com.zt.eweb.rbac.client.TenantApplicationService;
import com.zt.eweb.rbac.client.TenantQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 租户Controller
 *
 * @author haoxin
 * @date 2021-02-24
 **/
@RestController
@RequestMapping("/sys/tenant")
public class TenantController extends AbstractController {

    @Autowired
    private TenantQueryService tenantQueryService;

    @Autowired
    private TenantApplicationService tenantApplicationService;

    /**
     * 用户分页查询
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = tenantQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 禁用租户
     */
    @SysLog("禁用租户")
    @PostMapping("/disable/{id}")
    public Result disable(@PathVariable("id") String id){
        tenantApplicationService.disable(id);
        return Result.ok();
    }
}
