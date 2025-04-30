package com.zt.eweb.rbac.domain.service;

import com.zt.eweb.rbac.domain.model.captcha.Captcha;
import com.zt.eweb.rbac.domain.model.captcha.CaptchaCode;
import com.zt.eweb.rbac.domain.model.captcha.CaptchaRepository;
import com.zt.eweb.rbac.domain.model.captcha.Uuid;

/**
 * 验证码校验服务
 *
 * 
 * @date 2021-05-11
 **/
public class CaptchaValidateService {

    private CaptchaRepository captchaRepository;

    public CaptchaValidateService(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    /**
     * 校验验证码
     *
     * @param uuid
     * @param captchaCode
     * @return
     */
    public boolean validate(Uuid uuid, CaptchaCode captchaCode) {
        Captcha captcha = captchaRepository.find(uuid);
        if(captcha == null){
            return false;
        }
        //删除验证码
        captchaRepository.remove(uuid);
        return captcha.validate(captchaCode);
    }
}
