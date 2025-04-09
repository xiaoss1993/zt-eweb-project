package com.zt.eweb.modular.meta.adapter.dictionary;

import com.zt.eweb.framework.common.rest.PageResult;
import com.zt.eweb.framework.common.rest.Res;
import com.zt.eweb.framework.common.rest.ResResult;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.framework.common.utils.ValidationUtil;
import com.zt.eweb.framework.common.validation.ValidationGroup;
import com.zt.eweb.modular.meta.app.DictionaryService;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryDto;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典
 *
 * 
 * @since 2021/8/4
 */
@Tag(name = "字典")
@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @Operation(summary = "添加")
    @PostMapping("/add")
    public ResResult<DictionaryDto> add(@RequestBody DictionaryParam param) {
        ValidationUtil.validateParam(param, ValidationGroup.add.class);
        return Res.ok(dictionaryService.add(param));
    }

    @Operation(summary = "根据id删除")
    @DeleteMapping("/delete")
    public ResResult<Boolean> delete(Long id) {
        dictionaryService.delete(id);
        return Res.ok();
    }

    @Operation(summary = "更新")
    @PostMapping("/update")
    public ResResult<DictionaryDto> update(@RequestBody DictionaryParam param) {
        ValidationUtil.validateParam(param, ValidationGroup.edit.class);
        return Res.ok(dictionaryService.update(param));
    }

    @Operation(summary = "根据id获取")
    @GetMapping("/findById")
    public ResResult<DictionaryDto> findById(Long id) {
        return Res.ok(dictionaryService.findById(id));
    }

    @Operation(summary = "查询全部")
    @GetMapping("/findAll")
    public ResResult<List<DictionaryDto>> findAll() {
        return Res.ok(dictionaryService.findAll());
    }

    @Operation(summary = "分页")
    @GetMapping("/page")
    public ResResult<PageResult<DictionaryDto>> page(PageParam pageParam, DictionaryParam param) {
        return Res.ok(dictionaryService.page(pageParam, param));
    }

    @Operation(summary = "编码是否被使用")
    @GetMapping("/existsByCode")
    public ResResult<Boolean> existsByCode(String code) {
        return Res.ok(dictionaryService.existsByCode(code));
    }

    @Operation(summary = "编码是否被使用(不包含自己)")
    @GetMapping("/existsByCodeNotId")
    public ResResult<Boolean> existsByCode(String code, Long id) {
        return Res.ok(dictionaryService.existsByCode(code, id));
    }

}
