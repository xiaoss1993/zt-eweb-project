package com.zt.eweb.framework.exceptionhandler.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置
 *
 */
@Getter
@Setter
@ConfigurationProperties("eweb.common.exception")
public class ExceptionHandlerProperties {

    /** 是否显示详细异常信息 */
    private boolean showFullMessage;

}
