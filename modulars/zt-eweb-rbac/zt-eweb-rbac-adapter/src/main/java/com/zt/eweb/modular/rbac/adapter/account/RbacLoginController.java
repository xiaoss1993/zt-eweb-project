package com.zt.eweb.modular.rbac.adapter.account;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zt.eweb.framework.common.base.result.Response;
import com.zt.eweb.modular.rbac.client.dto.LoginDto;
import com.zt.eweb.modular.rbac.client.dto.RbacUserDto;
import com.zt.eweb.modular.rbac.client.manager.UserApplicationService;
import com.zt.eweb.modular.rbac.client.manager.UserQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 22:00 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Tag(name = "权限管理-认证授权")
@Slf4j
@RestController
@RequestMapping("/sys/auth")
@AllArgsConstructor
public class RbacLoginController {


    private final UserQueryService userQueryService;
    private final UserApplicationService userApplicationService;

    /**
     * 登录-用户名密码
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/v1/login")
    @ApiOperationSupport(order = 1)
    public Response<SaTokenInfo> login(@Validated @RequestBody LoginDto loginDto) {
        return userApplicationService.login(loginDto);
    }

    /**
     * 获取当前会话的token信息
     *
     * @return
     */
    @GetMapping("/v1/tokenInfo")
    @ApiOperationSupport(order = 2)
    public Response<SaTokenInfo> tokenInfo() {
        return Response.ok(StpUtil.getTokenInfo());
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/v1/userInfo")
    @ApiOperationSupport(order = 2)
    public Response<RbacUserDto> userInfo() {
        RbacUserDto rbacUserDto = userQueryService.getUserInfoById(StpUtil.getLoginIdAsLong());
        return Response.ok(rbacUserDto);
    }

    /**
     * 踢人下线
     *
     * @param token
     * @return
     */
    @GetMapping("/v1/kickOffline")
    @ApiOperationSupport(order = 2)
    public Response<Void> kickOffline(String token) {
        log.info("踢人下线，token:{}", token);
        if (StpUtil.getTokenValue().equals(token)) {
            return Response.error("500", "不能踢自己啊老弟");
        }
        StpUtil.logoutByTokenValue(token);
        return Response.ok();
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/v1/logout")
    @ApiOperationSupport(order = 3)
    public Response<Void> loginOut() {
        //获取token
        //   1. 尝试从request里读取 tokenName 默认值 satoken
        //   2. 尝试从请求体里面读取
        //   3. 尝试从header里读取
        //   4. 尝试从cookie里读取
        // 删除cookie
        StpUtil.logout();
        return Response.ok();
    }

}
