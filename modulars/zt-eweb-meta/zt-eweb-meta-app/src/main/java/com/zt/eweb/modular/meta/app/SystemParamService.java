package com.zt.eweb.modular.meta.app;

import com.zt.eweb.framework.common.rest.PageResult;
import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.modular.meta.client.dto.parameter.SystemParameterDto;
import com.zt.eweb.modular.meta.client.param.system.SystemParameterParam;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 16:34 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class SystemParamService {

  public void add(SystemParameterParam param) {

  }

  public void update(SystemParameterParam param) {

  }

  public PageResult<SystemParameterDto> page(PageParam pageParam, SystemParameterParam param) {
    return null;
  }

  public SystemParameterDto findById(Long id) {
    return null;
  }

  public void delete(Long id) {

  }

  public Boolean existsByKey(String key) {
    return null;
  }

  public Boolean existsByKey(String key, Long id) {
    return null;
  }

  public String findByParamKey(String key) {
    return null;
  }
}
