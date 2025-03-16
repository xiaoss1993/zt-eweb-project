package com.zt.eweb.modular.meta.adapter;

import com.wujiuye.flow.FlowHelper;
import com.wujiuye.flow.FlowType;
import com.wujiuye.flow.common.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/16 11:06
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/16      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@RestController
@Slf4j
@RequestMapping("/meta/info/api")
public class MetaApiQPSController {
    private FlowHelper flowHelper = new FlowHelper(FlowType.Hour);

    @GetMapping("/test")
    public void testApi() {
        try{
            long startTime = TimeUtil.currentTimeMillis();
            // 业务逻辑
            //    ......
            // 计算耗时
            long rt = TimeUtil.currentTimeMillis() - startTime;
            flowHelper.incrSuccess(rt);
        }catch (Exception e){
            flowHelper.incrException();
        }
    }
}
