package com.zt.eweb.modular.meta.adapter.datasource;

import cn.hutool.db.PageResult;
import com.zt.eweb.framework.common.rest.Res;
import com.zt.eweb.framework.common.rest.ResResult;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.modular.meta.app.DataResultSqlService;
import com.zt.eweb.modular.meta.app.SqlQueryService;
import com.zt.eweb.modular.meta.client.dto.dataresult.SqlQueryResult;
import com.zt.eweb.modular.meta.client.param.dataresult.SqlQueryParam;
import com.zt.eweb.modular.meta.infra.dal.dataobject.DataResultSql;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SQL查询
 *
 * 
 * @since 2023/3/9
 */
@Tag(name = "SQL查询")
@RestController
@RequestMapping("/data/result")
@RequiredArgsConstructor
public class DataResultController {

    private final DataResultSqlService dataResultSqlService;
    private final SqlQueryService sqlQueryService;


    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public ResResult<PageResult<DataResultSql>> page(){
        return Res.ok(dataResultSqlService.page());
    }

    @Operation(summary = "新建")
    @PostMapping("/add")
    public ResResult<Void> add(){
        dataResultSqlService.add();
        return Res.ok();
    }

    @Operation(summary = "修改")
    @PostMapping("/update")
    public ResResult<Void> update(){
        dataResultSqlService.update();
        return Res.ok();
    }

    @Operation(summary = "测试SQL解析和执行")
    @PostMapping("/test")
    public ResResult<Void> test(@RequestBody Map<String, Object> map) {
        dataResultSqlService.querySql(map);
        return Res.ok();
    }

//    @Operation(summary = "通过SQL查出结果字段")
//    @PostMapping("/queryFieldBySql")
//    public ResResult<List<String>> queryFieldBySql(@RequestBody SqlQueryParam param) {
//        return Res.ok(dataResultService.queryFieldBySql(param));
//    }

    @Operation(summary = "执行SQL查询语句")
    @PostMapping("/querySql")
    public ResResult<SqlQueryResult> querySql(@RequestBody SqlQueryParam param, PageParam pageParam){
        return Res.ok(sqlQueryService.query(param,pageParam));
    }

    @SneakyThrows
    @Operation(summary = "导出SQL查询的结果")
    @PostMapping("/exportQueryResult")
    public ResponseEntity<byte[]> exportQueryResult(@RequestBody SqlQueryParam param, @ParameterObject PageParam pageParam){
        byte[] bytes = sqlQueryService.exportQueryResult(param,pageParam);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
