package com.zt.eweb.rbac.adapter.controller.rbac;

import com.zt.eweb.framework.common.utils.CommonConstant;
import com.zt.eweb.framework.common.utils.Page;
import com.zt.eweb.framework.common.utils.validator.ValidatorUtils;
import com.zt.eweb.rbac.adapter.common.AbstractController;
import com.zt.eweb.rbac.adapter.common.Result;
import com.zt.eweb.rbac.adapter.util.log.SysLog;
import com.zt.eweb.rbac.client.RoleApplicationService;
import com.zt.eweb.rbac.client.RoleQueryService;
import com.zt.eweb.rbac.client.command.RoleCommand;
import com.zt.eweb.rbac.client.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 *
 * @author haoxin
 * @date 2021-02-18
 **/
@RestController
@RequestMapping("/rbac/role")
public class RoleController extends AbstractController {

    @Autowired
    private RoleQueryService roleQueryService;

    @Autowired
    private RoleApplicationService roleApplicationService;

    /**
     * 角色分页查询
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = roleQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }

    /**
     * 角色列表
     */
    @GetMapping("/select")
    public Result select(){
        List<RoleDTO> list = roleQueryService.listAll();
        return Result.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String id){
        RoleDTO role = roleQueryService.getById(id);
        return Result.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @PostMapping("/save")
    public Result save(@RequestBody RoleCommand roleCommand){
        ValidatorUtils.validateEntity(roleCommand);
        roleApplicationService.saveOrUpdate(roleCommand);
        return Result.ok();
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @PostMapping("/update")
    public Result update(@RequestBody RoleCommand roleCommand){
        ValidatorUtils.validateEntity(roleCommand);
        roleApplicationService.saveOrUpdate(roleCommand);
        return Result.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @PostMapping("/delete")
    public Result delete(@RequestBody String[] roleIds){
        roleApplicationService.deleteBatch(Arrays.asList(roleIds));
        return Result.ok();
    }

    /**
     * 禁用角色
     */
    @SysLog("禁用角色")
    @PostMapping("/disable/{id}")
    public Result disable(@PathVariable("id") String id){
        roleApplicationService.disable(id);
        return Result.ok();
    }
}
