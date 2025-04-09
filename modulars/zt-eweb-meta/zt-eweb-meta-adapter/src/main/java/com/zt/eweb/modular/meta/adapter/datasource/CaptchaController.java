package com.zt.eweb.modular.meta.adapter.datasource;

import com.zt.eweb.framework.common.annotation.IgnoreAuth;
import com.zt.eweb.framework.common.rest.Res;
import com.zt.eweb.framework.common.rest.ResResult;
import com.zt.eweb.modular.meta.app.CaptchaService;
import com.zt.eweb.modular.meta.client.dto.captcha.CaptchaDataResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码服务
 *
 * 
 * @since 2021/8/2
 */
@Tag(name = "验证码服务")
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

    @IgnoreAuth
    @Operation(summary = "获取图片验证码")
    @PostMapping("/imgCaptcha")
    public ResResult<CaptchaDataResult> imgCaptcha() {
        return Res.ok(captchaService.imgCaptcha());
    }

}
