package com.zt.eweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 模块名 :
 * 文件名 :    ZTEwebLiteApplication
 * 创建时间 : 2025/3/15 09:04
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/15      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@SpringBootApplication

@MapperScan(basePackages = {"com.zt.eweb.rbac.infrastructure.persistence.mapper"})
public class ZTEwebLiteApplication {
  public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ZTEwebLiteApplication.class, args);
        //这里可以验证一下 service 有没有成功注入
    // AnylineService service = context.getBean(AnylineService.class);
    }

}
