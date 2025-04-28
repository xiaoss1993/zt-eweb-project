package com.zt.eweb.rbac.adapter.controller.rbac;

import com.zt.eweb.framework.common.utils.validator.ValidatorUtils;
import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import com.zt.eweb.rbac.adapter.common.AbstractController;
import com.zt.eweb.rbac.adapter.common.Result;
import com.zt.eweb.rbac.adapter.util.log.SysLog;
import com.zt.eweb.rbac.client.AuthenticationApplicationService;
import com.zt.eweb.rbac.client.command.RegisterTenantCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册Controller
 *
 * @author haoxin
 * @date 2021-02-13
 **/
@RestController
public class RegisterController extends AbstractController {

    @Autowired
    private AuthenticationApplicationService authenticationApplicationService;

    /**
     * 注册租户
     */
    @SysLog("注册租户")
    @PostMapping("/rbac/registerTenant")
    public Result registerTenantAndUser(@RequestBody RegisterTenantCommand registerTenantCommand) {
        ValidatorUtils.validateEntity(registerTenantCommand, AddGroup.class);
        authenticationApplicationService.registerTenant(registerTenantCommand);
        return Result.ok();
    }
}
