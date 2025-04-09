package com.zt.eweb.modular.meta.app;

import com.zt.eweb.framework.common.rest.PageResult;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryDto;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryParam;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 16:36 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class DictionaryService {

  public DictionaryDto add(DictionaryParam param) {
    return null;
  }

  public void delete(Long id) {

  }

  public DictionaryDto update(DictionaryParam param) {
    return null;
  }

  public DictionaryDto findById(Long id) {
    return null;
  }

  public List<DictionaryDto> findAll() {
    return null;
  }

  public PageResult<DictionaryDto> page(PageParam pageParam, DictionaryParam param) {
    return null;
  }

  public Boolean existsByCode(String code) {
    return null;
  }

  public Boolean existsByCode(String code, Long id) {
    return null;
  }
}
