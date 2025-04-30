package com.zt.eweb.rbac.adapter.controller.rbac;

import com.zt.eweb.rbac.adapter.common.AbstractController;
import com.zt.eweb.rbac.adapter.common.Result;
import com.zt.eweb.framework.common.utils.validator.ValidatorUtils;
import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import com.zt.eweb.framework.common.utils.validator.group.UpdateGroup;
import com.zt.eweb.rbac.adapter.util.log.SysLog;
import com.zt.eweb.rbac.client.PermissionApplicationService;
import com.zt.eweb.rbac.client.PermissionQueryService;
import com.zt.eweb.rbac.client.command.PermissionCommand;
import com.zt.eweb.rbac.client.dto.PermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 权限Controller
 *
 * 
 * @date 2021-02-16
 **/
@RestController
@RequestMapping("/rbac/permission")
public class PermissionController extends AbstractController {

    @Autowired
    private PermissionApplicationService permissionApplicationService;

    @Autowired
    private PermissionQueryService permissionQueryService;

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public Result nav(){
        List<PermissionDTO> menuList = permissionQueryService.getUserMenuTree(getUser().getPermissionIds());
        Set<String> permissions = getUser().getPermissionCodes();
        return Result.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有权限列表
     */
    @GetMapping("/list")
    public Result list(){
        List<PermissionDTO> permissionList = permissionQueryService.listAllPermission();
        return Result.ok().put("permissionList", permissionList);
    }

    /**
     * 选择菜单
     */
    @GetMapping("/selectMenu")
    public Result selectMenu(){
        List<PermissionDTO> menuList = permissionQueryService.listAllMenu();
        return Result.ok().put("menuList", menuList);
    }

    /**
     * 权限信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String id){
        PermissionDTO permission = permissionQueryService.getById(id);
        return Result.ok().put("permission", permission);
    }

    /**
     * 保存权限
     */
    @SysLog("保存权限")
    @PostMapping("/save")
    public Result save(@RequestBody PermissionCommand permissionCommand){
        ValidatorUtils.validateEntity(permissionCommand, AddGroup.class);
        permissionApplicationService.saveOrUpdate(permissionCommand);
        return Result.ok();
    }

    /**
     * 修改权限
     */
    @SysLog("修改权限")
    @PostMapping("/update")
    public Result update(@RequestBody PermissionCommand permissionCommand){
        ValidatorUtils.validateEntity(permissionCommand, UpdateGroup.class);
        permissionApplicationService.saveOrUpdate(permissionCommand);
        return Result.ok();
    }

    /**
     * 删除权限
     */
    @SysLog("删除权限")
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id){
        permissionApplicationService.delete(id);
        return Result.ok();
    }

    /**
     * 禁用权限
     */
    @SysLog("禁用权限")
    @PostMapping("/disable/{id}")
    public Result disable(@PathVariable("id") String id){
        permissionApplicationService.disable(id);
        return Result.ok();
    }

}
