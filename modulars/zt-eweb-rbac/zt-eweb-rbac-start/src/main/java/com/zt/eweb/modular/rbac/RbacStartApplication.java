package com.zt.eweb.modular.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/3/28 17:04 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/3/28      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RbacStartApplication {

  public static void main(String[] args) {
    SpringApplication.run(RbacStartApplication.class);
  }
}
