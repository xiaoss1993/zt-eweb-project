package com.zt.eweb.rbac.adapter.web.controller.rbac;


import com.zt.eweb.framework.common.utils.CommonConstant;
import com.zt.eweb.framework.common.utils.Page;
import com.zt.eweb.rbac.client.LogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zt.eweb.rbac.adapter.web.common.AbstractController;
import com.zt.eweb.rbac.adapter.web.common.Result;

import java.util.Map;

/**
 * 系统日志Controller
 *
 * @author haoxin
 * @date 2021-02-04
 **/
@RestController
@RequestMapping("/sys/log")
public class LogController extends AbstractController {

    @Autowired
    private LogQueryService logQueryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        Page page = logQueryService.queryPage(params);
        return Result.ok().put(CommonConstant.PAGE, page);
    }
}
