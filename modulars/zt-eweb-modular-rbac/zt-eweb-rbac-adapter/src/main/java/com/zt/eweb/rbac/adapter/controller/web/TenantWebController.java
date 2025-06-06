package com.zt.eweb.rbac.adapter.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sizt
 * @description: TODO
 * @date 2025/4/22 08:39
 */
@Controller
@RequestMapping(value = "/rbac/tenant")
public class TenantWebController {
    private static final String  prefix ="/rbac/role";


    @GetMapping
    public String   index(){
        return prefix +"/index";
    }

    @GetMapping("/edit")
    public String   edit(){
        return prefix +"/edit";
    }

}
