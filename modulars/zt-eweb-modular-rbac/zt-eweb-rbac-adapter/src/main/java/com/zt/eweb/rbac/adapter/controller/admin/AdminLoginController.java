package com.zt.eweb.rbac.adapter.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import com.zt.eweb.framework.common.utils.TenantContext;
import com.zt.eweb.rbac.adapter.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sizt
 * @description: TODO
 * @date 2025/4/21 18:15
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("admin".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            TenantContext.setTenantId("1");
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping
    public String index() {
        return "index";
    }
}
