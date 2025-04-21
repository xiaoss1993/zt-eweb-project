package com.zt.eweb.rbac.adapter.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sizt
 * @description: TODO
 * @date 2025/4/21 18:15
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController{

    @GetMapping("/login")
    public String   login(){
        return "login";
    }

    @GetMapping("/")
    public String   index(){
        return "index";
    }
}
