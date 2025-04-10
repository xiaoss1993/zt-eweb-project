package com.zt.eweb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 */
@SuppressWarnings("SpringComponentScan")
@SpringBootApplication(scanBasePackages = {"${eweb.info.base-package}.server", "${eweb.info.base-package}.module"})
public class YudaoServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(YudaoServerApplication.class, args);
//        new SpringApplicationBuilder(YudaoServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);

    }

}
