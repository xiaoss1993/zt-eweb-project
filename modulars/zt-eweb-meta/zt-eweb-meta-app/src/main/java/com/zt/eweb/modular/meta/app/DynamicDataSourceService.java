package com.zt.eweb.modular.meta.app;

import com.zt.eweb.framework.common.rest.PageResult;
import com.zt.eweb.framework.common.rest.dto.KeyValue;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.modular.meta.client.dto.dynamicsource.DynamicDataSourceDto;
import com.zt.eweb.modular.meta.client.param.dynamicsource.DynamicDataSourceParam;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 16:15 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class DynamicDataSourceService {

  public void add(DynamicDataSourceParam param) {

  }

  public void update(DynamicDataSourceParam param) {

  }

  public PageResult<DynamicDataSourceDto> page(PageParam pageParam,
      DynamicDataSourceParam dynamicDataSourceParam) {
    return null;
  }

  public DynamicDataSourceDto findById(Long id) {
    return null;
  }

  public Boolean existsByCode(String code) {
    return null;
  }

  public Boolean existsByCode(String code, Long id) {
    return null;
  }

  public List<DynamicDataSourceDto> findAll() {
    return null;
  }

  public void delete(Long id) {

  }

  public String testConnection(DynamicDataSourceParam param) {
    return null;
  }

  public String testConnection(Long aLong) {
    return null;
  }

  public void addDynamicDataSourceById(Long id) {

  }

  public Boolean existsByDataSourceKey(String code) {
    return null;
  }

  public List<KeyValue> findAllDataSource() {
    return null;
  }

  public void removeDataSourceByKey(String key) {
  }
}
