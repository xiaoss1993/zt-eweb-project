package com.zt.eweb.framework.mybatis.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Slf4j
public class I18n {

  private static MessageSource messageSource;

  public static String message(String code, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    if (messageSource == null) {
      messageSource = ContextHolder.getBean(MessageSource.class);
    }
    String content = messageSource.getMessage(code, args, locale);
    return content;
  }

}
