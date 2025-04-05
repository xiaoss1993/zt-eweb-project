package com.zt.eweb.modular.admin.adapter.web;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 11:19 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *  后台管理页面控制器
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@ApiSupport(order = 5)
@Controller
@Slf4j
public class ZTAdminIndexController {

  @GetMapping({"/admin", "/admin/index", "/admin/"})
  public String adminIndex() {
    return "redirect:/admin/index.html";
  }

  @GetMapping({"/"})
  public String home() {
    return "redirect:/admin/home/index.html";
  }
}
