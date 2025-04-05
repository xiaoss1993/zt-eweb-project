package com.zt.eweb.modular.admin.adapter.web;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.wf.captcha.ArithmeticCaptcha;
import com.zt.eweb.framework.cache.IEasyCache;
import com.zt.eweb.framework.common.base.result.Response;
import com.zt.eweb.framework.common.config.EasyConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/4 11:19 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 * 后台管理页面控制器
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/4      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@ApiSupport(order = 5)
@Controller
@RequiredArgsConstructor
@Slf4j
public class ZTAdminIndexController {
    private final IEasyCache iEasyCache;
    private final EasyConfig config;


    @GetMapping({"/admin", "/admin/index", "/admin/"})
    public String adminIndex() {
        return "redirect:/admin/index.html";
    }

    @GetMapping({"/"})
    public String home() {
        return "redirect:/admin/";
    }


    /**
     * 详情参考：<a href="https://gitee.com/whvse/EasyCaptcha">...</a>
     */
    @GetMapping("/captcha")
    @ResponseBody
//  @RateLimit(key = "captcha", period = 1, limit = 2, limitType = LimitType.IP)
    public Response<Dict> captcha() {
//        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
//        中文gif类型
//        ChineseGifCaptcha specCaptcha = new ChineseGifCaptcha(130, 48, 4);
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        captcha.text();  // 获取运算的结果：5
        String verCode = captcha.text().toLowerCase();
        String uid = IdUtil.simpleUUID();
        log.info("当前uid:{},验证码：{}", uid, verCode);
        // 前后端分离，这时还未有会话信息，用于确定uid和验证码的一一映射关系，防止多人串码
        // 把uuid和图片码一起给前端，验证的时候再一起回来
        iEasyCache.put(uid, verCode, 3 * 60L);
        return Response.ok(Dict.create().set("uid", uid).set("image", captcha.toBase64()));
    }
}
