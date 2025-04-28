package com.zt.eweb.rbac.adapter.controller.rbac;


import com.zt.eweb.framework.common.utils.Page;
import com.zt.eweb.framework.common.utils.validator.ValidatorUtils;
import com.zt.eweb.framework.common.utils.validator.group.AddGroup;
import com.zt.eweb.framework.common.utils.validator.group.UpdateGroup;
import com.zt.eweb.rbac.adapter.common.AbstractController;
import com.zt.eweb.rbac.adapter.common.Result;
import com.zt.eweb.rbac.adapter.util.log.SysLog;
import com.zt.eweb.rbac.client.UserApplicationService;
import com.zt.eweb.rbac.client.UserQueryService;
import com.zt.eweb.rbac.client.command.PasswordCommand;
import com.zt.eweb.rbac.client.command.UserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 用户Controller
 *
 * @author haoxin
 * @date 2021-02-20
 **/
@RestController
@RequestMapping("/rbac/user")
public class UserController extends AbstractController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserQueryService userQueryService;

    /**
     * 用户分页查询
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = userQueryService.queryPage(params);
        return Result.ok().put("data", page.getList());
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info(){
        return Result.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public Result changePassword(@RequestBody PasswordCommand passwordCommand){
        ValidatorUtils.validateEntity(passwordCommand);
        passwordCommand.setUserId(getUser().getId());
        userApplicationService.changePassword(passwordCommand);
        return Result.ok();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String id){
        return Result.ok().put("user", userQueryService.find(id));
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody UserCommand userCommand){
        ValidatorUtils.validateEntity(userCommand, AddGroup.class);
        userApplicationService.save(userCommand);
        return Result.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @PostMapping("/update")
    public Result update(@RequestBody UserCommand userCommand){
        ValidatorUtils.validateEntity(userCommand, UpdateGroup.class);
        userApplicationService.update(userCommand);
        return Result.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    public Result delete(@RequestBody String[] userIds){
        userApplicationService.deleteBatch(Arrays.asList(userIds));
        return Result.ok();
    }

    /**
     * 禁用用户
     */
    @SysLog("禁用用户")
    @PostMapping("/disable/{id}")
    public Result disable(@PathVariable("id") String id){
        userApplicationService.disable(id);
        return Result.ok();
    }
}
