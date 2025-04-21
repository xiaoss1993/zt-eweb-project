package com.zt.eweb.rbac.adapter.web.controller.rbac;

import com.zt.eweb.framework.common.utils.CommonConstant;
import com.zt.eweb.framework.common.utils.redis.RedisUtils;
import com.zt.eweb.framework.common.utils.validator.ValidatorUtils;
import com.zt.eweb.rbac.adapter.web.common.AbstractController;
import com.zt.eweb.rbac.adapter.web.common.Result;
import com.zt.eweb.rbac.adapter.web.util.log.SysLog;
import com.zt.eweb.rbac.client.AuthenticationApplicationService;
import com.zt.eweb.rbac.client.command.AccountLoginCommand;
import com.zt.eweb.rbac.client.command.MobileLoginCommand;
import com.zt.eweb.rbac.client.dto.LoginSuccessDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录Controller
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@RestController
public class LoginController extends AbstractController {

    @Autowired
    private AuthenticationApplicationService authenticationApplicationService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = authenticationApplicationService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 账号登录
     */
    @SysLog("账号登录")
    @PostMapping("/sys/loginByAccount")
    public Result loginByAccount(@RequestBody AccountLoginCommand accountLoginCommand) {
        ValidatorUtils.validateEntity(accountLoginCommand);
        LoginSuccessDTO loginSuccessDTO = authenticationApplicationService.loginByAccount(accountLoginCommand);
        return Result.ok(loginSuccessDTO);
    }


    /**
     * 手机号登录
     */
    @SysLog("手机号登录")
    @PostMapping("/sys/loginByMobile")
    public Result loginByMobile(@RequestBody MobileLoginCommand mobileLoginCommand) {
        ValidatorUtils.validateEntity(mobileLoginCommand);
        String verificationCodeRedis = redisUtils.get(CommonConstant.REDIS_PHONE_CODE + mobileLoginCommand.getMobile());
        mobileLoginCommand.setCorrectVerificationCode(verificationCodeRedis);
        LoginSuccessDTO loginSuccessDTO = authenticationApplicationService.loginByMobile(mobileLoginCommand);
        return Result.ok(loginSuccessDTO);
    }

    /**
     * 退出
     */
    @SysLog("退出")
    @PostMapping("/sys/logout")
    public Result logout() {
        authenticationApplicationService.logout(getUser().getId());
        return Result.ok();
    }

}
