package com.zt.eweb.modular.meta.adapter.datasource;

import com.zt.eweb.framework.common.annotation.IgnoreAuth;
import com.zt.eweb.framework.common.rest.PageResult;
import com.zt.eweb.framework.common.rest.Res;
import com.zt.eweb.framework.common.rest.ResResult;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.framework.common.utils.ValidationUtil;
import com.zt.eweb.framework.common.validation.ValidationGroup;
import com.zt.eweb.modular.meta.app.SystemParamService;
import com.zt.eweb.modular.meta.client.dto.parameter.SystemParameterDto;
import com.zt.eweb.modular.meta.client.param.system.SystemParameterParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统参数
 *
 * 
 * @since 2021/10/25
 */
@Tag(name = "系统参数")
@RestController
@RequestMapping("/system/param")
@RequiredArgsConstructor
public class SystemParamController {

    private final SystemParamService systemParamService;

    @Operation(summary = "添加")
    @PostMapping("/add")
    public ResResult<Void> add(@RequestBody SystemParameterParam param) {
        ValidationUtil.validateParam(param, ValidationGroup.add.class);
        systemParamService.add(param);
        return Res.ok();
    }

    @Operation(summary = "更新")
    @PostMapping("/update")
    public ResResult<Void> update(@RequestBody SystemParameterParam param) {
        ValidationUtil.validateParam(param, ValidationGroup.edit.class);
        systemParamService.update(param);
        return Res.ok();
    }

    @Operation(summary = "分页")
    @GetMapping("/page")
    public ResResult<PageResult<SystemParameterDto>> page(@ParameterObject PageParam pageParam,
                                                          @ParameterObject SystemParameterParam param) {
        return Res.ok(systemParamService.page(pageParam, param));
    }

    @Operation(summary = "获取单条")
    @GetMapping("/findById")
    public ResResult<SystemParameterDto> findById(@Parameter(description = "主键") Long id) {
        return Res.ok(systemParamService.findById(id));
    }

    @Operation(summary = "删除")
    @DeleteMapping("/delete")
    public ResResult<Void> delete(Long id) {
        systemParamService.delete(id);
        return Res.ok();
    }

    @Operation(summary = "判断编码是否存在")
    @GetMapping("/existsByKey")
    public ResResult<Boolean> existsByKey(String key) {
        return Res.ok(systemParamService.existsByKey(key));
    }

    @Operation(summary = "判断编码是否存在(不包含自己)")
    @GetMapping("/existsByKeyNotId")
    public ResResult<Boolean> existsByKeyNotId(String key, Long id) {
        return Res.ok(systemParamService.existsByKey(key, id));
    }

    @IgnoreAuth
    @Operation(summary = "根据键名获取键值")
    @GetMapping("/findByParamKey")
    public ResResult<String> findByParamKey(String key) {
        return Res.ok(systemParamService.findByParamKey(key));
    }

}
