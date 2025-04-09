//package com.zt.eweb.modular.meta.adapter.datasource;
//
//import cn.hutool.core.io.IoUtil;
//import com.zt.eweb.framework.common.rest.Res;
//import com.zt.eweb.framework.common.rest.ResResult;
//import com.zt.eweb.modular.meta.app.ChinaWordService;
//import com.zt.eweb.modular.meta.client.dto.chinaword.ChinaWordDto;
//import com.zt.eweb.modular.meta.client.dto.chinaword.ChinaWordVerifyResult;
//import com.zt.eweb.modular.meta.client.param.chinaword.ChinaWordParam;
//import com.zt.eweb.modular.meta.client.param.chinaword.ChinaWordVerifyParam;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 敏感词
// *
// * @since 2023-08-09
// */
//@Tag(name ="敏感词管理")
//@RestController
//@RequestMapping("/chinaword")
//@RequiredArgsConstructor
//public class ChinaWordController {
//    private final ChinaWordService chinaWordService;
//
//    @Operation( summary = "添加")
//    @PostMapping(value = "/add")
//    public ResResult<Void> add(@RequestBody ChinaWordParam param){
//        chinaWordService.add(param);
//        return Res.ok();
//    }
//
//    @Operation( summary = "修改")
//    @PostMapping(value = "/update")
//    public ResResult<Void> update(@RequestBody ChinaWordParam param){
//        chinaWordService.update(param);
//        return Res.ok();
//    }
//
//    @Operation( summary = "删除")
//    @DeleteMapping(value = "/delete")
//    public ResResult<Void> delete(Long id){
//        chinaWordService.delete(id);
//        return Res.ok();
//    }
//
//    @Operation( summary = "通过ID查询")
//    @GetMapping(value = "/findById")
//    public ResResult<ChinaWordDto> findById(Long id){
//        return Res.ok(chinaWordService.findById(id));
//    }
//
//    @Operation( summary = "刷新缓存")
//    @PostMapping(value = "/refresh")
//    public ResResult<ChinaWordDto> refresh(){
//        chinaWordService.refresh();
//        return Res.ok();
//    }
//
//    @Operation( summary = "测试敏感词效果")
//    @PostMapping(value = "/verify")
//    public ResResult<ChinaWordVerifyResult> verify(@RequestBody ChinaWordVerifyParam param){
//        return Res.ok(chinaWordService.verify(param.getText(),param.getSkip(),param.getSymbol()));
//    }
//
//    @Operation( summary = "查询所有")
//    @GetMapping(value = "/findAll")
//    public ResResult<List<ChinaWordDto>> findAll(){
//        return Res.ok(chinaWordService.findAll());
//    }
//
//    @Operation( summary = "分页查询")
//    @GetMapping(value = "/page")
//    public ResResult<PageResult<ChinaWordDto>> page(PageParam pageParam, ChinaWordParam query){
//        return Res.ok(chinaWordService.page(pageParam,query));
//    }
//
//    @Operation(summary = "批量导入")
//    @PostMapping("/importBatch")
//    public ResResult<Void> local(MultipartFile file, String type) throws IOException {
//        chinaWordService.importBatch(file, type);
//        return Res.ok();
//    }
//
//    @Operation(summary = "获取模板")
//    @GetMapping("/getTemplate")
//    public ResponseEntity<byte[]> getTemplate() throws IOException {
//        InputStream is = Files.newInputStream(new File("D:/data/xxxx.xlsx").toPath());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return new ResponseEntity<>(IoUtil.readBytes(is), headers, HttpStatus.OK);
//    }
//}
