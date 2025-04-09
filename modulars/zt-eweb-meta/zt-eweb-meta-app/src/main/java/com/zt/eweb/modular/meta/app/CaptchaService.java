package com.zt.eweb.modular.meta.app;

import cn.hutool.core.util.RandomUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.zt.eweb.modular.meta.client.dto.captcha.CaptchaDataResult;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 16:03 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {

  /** redis key前缀 */
  private final String imgCaptchaPrefix = "login:captcha:img";

  /** 手机验证码前缀 */
  private final String smsCaptchaPrefix = "phone:captcha:";

  /** 邮箱验证码前缀 */
  private final String emailCaptchaPrefix = "email:captcha:";

  private final RedisTemplate<String,String> redisTemplate;

  /**
   * 获取图片验证码
   */
  public CaptchaDataResult imgCaptcha() {
    String key = RandomUtil.randomString(16);
    // 几位数运算，默认是两位
    ArithmeticCaptcha captcha = new ArithmeticCaptcha(105, 35);
    // 获取运算的结果
    String text = captcha.text();
    log.info("获取图片验证码: {}", text);
    redisTemplate.opsForValue().set(imgCaptchaPrefix + key, text, 5 * 60 * 1000);
    return new CaptchaDataResult().setCaptchaKey(key).setCaptchaData(captcha.toBase64());
  }

  /**
   * 校验图片验证码
   * @param key 验证码Key
   */
  public boolean validateImgCaptcha(String key, String captcha) {
    // 比较验证码是否正确
    String captchaByRedis =  redisTemplate.opsForValue().get(imgCaptchaPrefix + key);
    return Objects.equals(captcha, captchaByRedis);
  }

  /**
   * 将图片验证码设置为失效
   * @param key 验证码Key
   */
  public void deleteImgCaptcha(String key) {
    redisTemplate.delete(imgCaptchaPrefix + key);
  }

  /**
   * 发送手机验证码
   * @param phone 手机号
   * @param timeoutSec 超时时间
   * @param type 业务类型, 用来区分不同业务的短信验证码
   * @return 验证码
   */
  public int sendSmsCaptcha(String phone, long timeoutSec, String type) {
    int captcha = RandomUtil.randomInt(100000, 1000000);
    log.info("短信验证码: {}", captcha);
    redisTemplate.opsForValue().set(getSmsCaptchaPrefix(type) + phone, String.valueOf(captcha), timeoutSec * 1000);
    return captcha;
  }

  /**
   * 验证手机发送的验证码是否还在有效时间内
   */
  public boolean existsSmsCaptcha(String phone, String type) {
    return redisTemplate.getExpire(getSmsCaptchaPrefix(type) + phone)>0;
  }

  /**
   * 校验手机验证码
   */
  public boolean validateSmsCaptcha(String phone, String captcha, String type) {
    // 比较验证码是否正确
    String captchaByRedis = redisTemplate.opsForValue().get(getSmsCaptchaPrefix(type) + phone);
    return Objects.equals(captcha, captchaByRedis);
  }

  /**
   * 将手机验证码验证码设置为失效
   */
  public void deleteSmsCaptcha(String phone, String type) {
    redisTemplate.delete(getSmsCaptchaPrefix(type) + phone);
  }

  /**
   * 获取手机验证码前缀
   */
  private String getSmsCaptchaPrefix(String type) {
    return smsCaptchaPrefix + type + ":";
  }

  /**
   * 发送邮箱验证码
   */
  public int sendEmailCaptcha(String email, long timeoutSec, String type) {
    int captcha = RandomUtil.randomInt(100000, 1000000);
    log.info("邮箱验证码: {}", captcha);
    redisTemplate.opsForValue().set(getEmailCaptchaPrefix(type) + email, String.valueOf(captcha), timeoutSec * 1000);
    return captcha;
  }

  /**
   * 邮箱发送的验证码是否还有效
   */
  public boolean existsEmailCaptcha(String email, String type) {
    return redisTemplate.getExpire(getEmailCaptchaPrefix(type) + email)>0;
  }

  /**
   * 校验邮箱验证码
   */
  public boolean validateEmailCaptcha(String email, String captcha, String type) {
    // 比较验证码是否正确
    String captchaByRedis = redisTemplate.opsForValue().get(getEmailCaptchaPrefix(type) + email);
    return Objects.equals(captcha, captchaByRedis);
  }

  /**
   * 失效邮箱验证码
   */
  public void deleteEmailCaptcha(String email, String type) {
    redisTemplate.delete(getEmailCaptchaPrefix(type) + email);
  }

  /**
   * 获取邮箱验证码前缀
   */
  private String getEmailCaptchaPrefix(String type) {
    return emailCaptchaPrefix + type + ":";
  }

}
