package com.zt.eweb.modular.rbac.adapter.permission;

import com.zt.eweb.framework.common.base.result.Result;
import com.zt.eweb.modular.rbac.client.command.PermissionCommand;
import com.zt.eweb.modular.rbac.client.dto.PermissionDTO;
import com.zt.eweb.modular.rbac.client.manager.PermissionApplicationService;
import com.zt.eweb.modular.rbac.client.manager.PermissionQueryService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 22:06 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Tag(name = "系统模块-权限管理")
@RestController
@RequestMapping("/rbac/permission")
public class RbacPermissionController {
    @Autowired
    private PermissionApplicationService permissionApplicationService;

    @Autowired
    private PermissionQueryService permissionQueryService;


    /**
     * 导航菜单
     */
    @Schema(name = "导航菜单")
    @GetMapping("/nav")
    public Result nav() {
        return Result.ok();
    }

    /**
     * 所有权限列表
     */
    @Schema(name = "所有权限列表")
    @GetMapping("/list")
    public Result list() {

        return Result.ok();
    }

    /**
     * 选择菜单
     */
    @Schema(name = "选择菜单")
    @GetMapping("/selectMenu")
    public Result selectMenu() {
        List<PermissionDTO> menuList = permissionQueryService.listAllMenu();
        return Result.ok();
    }
    /**
     * 权限信息
     */
    @Schema(name = "权限信息")
    @GetMapping("/info/{id}")

    public Result info(@PathVariable("id") String id) {
        PermissionDTO permission = permissionQueryService.getById(id);
        return Result.ok(permission);
    }

    /**
     * 保存权限
     */
    @Schema(name = "保存权限")
    @PostMapping("/save")
    public Result save(@RequestBody PermissionCommand permissionCommand) {
      //  ValidatorUtils.validateEntity(permissionCommand, AddGroup.class);
        permissionApplicationService.saveOrUpdate(permissionCommand);
        return Result.ok();
    }

    /**
     * 修改权限
     */
    @Schema(name ="修改权限")

    @PostMapping("/update")
    public Result update(@RequestBody PermissionCommand permissionCommand) {
      //  ValidatorUtils.validateEntity(permissionCommand, UpdateGroup.class);
        permissionApplicationService.saveOrUpdate(permissionCommand);
        return Result.ok();
    }

    /**
     * 删除权限
     */
    @Schema(name ="删除权限")

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        permissionApplicationService.delete(id);
        return Result.ok();
    }

    /**
     * 禁用权限
     */
    @Schema(name ="禁用权限")
    @PostMapping("/disable/{id}")
    public Result disable(@PathVariable("id") String id) {
        permissionApplicationService.disable(id);
        return Result.ok();
    }

}
