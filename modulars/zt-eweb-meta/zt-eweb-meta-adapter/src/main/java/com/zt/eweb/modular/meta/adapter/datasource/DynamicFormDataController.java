package com.zt.eweb.modular.meta.adapter.datasource;

import com.zt.eweb.modular.meta.app.DynamicFormDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @since 2022/8/13
 */
@Tag(name = "动态表单数据")
@RestController
@RequestMapping("/dynamic/form/data")
@RequiredArgsConstructor
public class DynamicFormDataController {

    private final DynamicFormDataService dynamicFormDataService;

}
