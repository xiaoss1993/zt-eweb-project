package com.zt.eweb.modular.rbac.adapter.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.eweb.framework.common.base.result.Result;
import com.zt.eweb.modular.rbac.client.command.RoleCommand;
import com.zt.eweb.modular.rbac.client.dto.RoleDTO;
import com.zt.eweb.modular.rbac.client.manager.RoleApplicationService;
import com.zt.eweb.modular.rbac.client.manager.RoleQueryService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
@Tag(name = "角色管理")
@RestController
@RequestMapping("/rbac/role")
public class RbacRoleController {


    @Autowired
    private RoleQueryService roleQueryService;

    @Autowired
    private RoleApplicationService roleApplicationService;

    /**
     * 角色分页查询
     */
    @Schema(name = "角色分页查询")
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        Page page = roleQueryService.queryPage(params);
        return Result.ok(page);
    }

    /**
     * 角色列表
     */
    @Schema(name = "角色列表")
    @GetMapping("/select")
    public Result select() {
        List<RoleDTO> list = roleQueryService.listAll();
        return Result.ok( list);
    }

    /**
     * 角色信息
     */
    @Schema(name = "角色信息")
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String id) {
        RoleDTO role = roleQueryService.getById(id);
        return Result.ok( role);
    }

    /**
     * 保存角色
     */
    @Schema(name = "保存角色")
    @PostMapping("/save")
    public Result save(@RequestBody RoleCommand roleCommand) {
     //   ValidatorUtils.validateEntity(roleCommand);
        roleApplicationService.saveOrUpdate(roleCommand);
        return Result.ok();
    }

    /**
     * 修改角色
     */
    @Schema(name ="修改角色")
    @PostMapping("/update")
    public Result update(@RequestBody RoleCommand roleCommand) {
      //  ValidatorUtils.validateEntity(roleCommand);
        roleApplicationService.saveOrUpdate(roleCommand);
        return Result.ok();
    }

    /**
     * 删除角色
     */
    @Schema(name ="删除角色")
    @PostMapping("/delete")
    public Result delete(@RequestBody String[] roleIds) {
        roleApplicationService.deleteBatch(Arrays.asList(roleIds));
        return Result.ok();
    }

    /**
     * 禁用角色
     */
    @Schema(name ="禁用角色")
    @PostMapping("/disable/{id}")
    public Result disable(@PathVariable("id") String id) {
        roleApplicationService.disable(id);
        return Result.ok();
    }
}
