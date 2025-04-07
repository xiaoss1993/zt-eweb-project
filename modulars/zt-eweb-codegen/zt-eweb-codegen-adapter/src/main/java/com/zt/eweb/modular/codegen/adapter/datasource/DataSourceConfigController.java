package com.zt.eweb.modular.codegen.adapter.datasource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/4/7 11:21
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/4/7      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gen/datasource-config")
@Tag(name = "数据源管理")
public class DataSourceConfigController {

}
