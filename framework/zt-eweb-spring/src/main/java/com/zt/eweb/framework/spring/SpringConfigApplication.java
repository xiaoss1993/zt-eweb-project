package com.zt.eweb.framework.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring默认配置
 */
@EnableScheduling
@EnableRetry
@ComponentScan
public class SpringConfigApplication {

}
