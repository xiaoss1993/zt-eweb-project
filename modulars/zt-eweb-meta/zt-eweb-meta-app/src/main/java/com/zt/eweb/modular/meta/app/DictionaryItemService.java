package com.zt.eweb.modular.meta.app;

import com.zt.eweb.framework.common.rest.PageResult;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryItemDto;
import com.zt.eweb.modular.meta.client.dto.dict.DictionaryItemSimpleDto;
import com.zt.eweb.modular.meta.client.param.dict.DictionaryItemParam;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 16:37 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class DictionaryItemService {

  public DictionaryItemDto add(DictionaryItemParam param) {
    return null;
  }

  public DictionaryItemDto update(DictionaryItemParam param) {
    return null;
  }

  public void delete(Long id) {
  }

  public DictionaryItemDto findById(Long id) {
    return null;
  }

  public List<DictionaryItemDto> findByDictionaryId(Long dictionaryId) {
    return null;
  }

  public PageResult<DictionaryItemDto> pageByDictionaryId(Long dictId, PageParam pageParam) {
    return null;
  }

  public List<DictionaryItemSimpleDto> findAll() {
    return null;
  }

  public List<DictionaryItemSimpleDto> findAllByEnable() {
    return null;
  }

  public Boolean existsByCode(String code, Long dictId) {
    return null;
  }

  public Boolean existsByCode(String code, Long dictId, Long id) {
    return null;
  }
}
