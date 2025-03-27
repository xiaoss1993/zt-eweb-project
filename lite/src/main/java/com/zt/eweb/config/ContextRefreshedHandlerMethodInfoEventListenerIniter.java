//package com.zt.eweb.config;
//
///**
// * 启动的时候,显示 路径 method等 信息
// */
//
//import static java.util.Collections.emptyMap;
//
//import com.feilong.spring.aop.log.UseTimeLogableAspect;
//import com.feilong.spring.web.event.ContextRefreshedHandlerMethodInfoEventListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class ContextRefreshedHandlerMethodInfoEventListenerIniter {
//
//  @Bean("contextRefreshedHandlerMethodInfoEventListener")
//  public ContextRefreshedHandlerMethodInfoEventListener init() {
//    ContextRefreshedHandlerMethodInfoEventListener bean = new ContextRefreshedHandlerMethodInfoEventListener();
//    bean.setAnnotationAndAnnotationToStringBuilderMap(emptyMap());
//    return bean;
//  }
//  @Bean
//  public UseTimeLogableAspect useTimeLogableAspect(){
//    return  new UseTimeLogableAspect();
//  }
//}
