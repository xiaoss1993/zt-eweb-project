package com.zt.eweb.modular.meta.app;

import com.zt.eweb.framework.common.rest.param.PageParam;
import com.zt.eweb.modular.meta.client.dto.dataresult.SqlQueryResult;
import com.zt.eweb.modular.meta.client.param.dataresult.SqlQueryParam;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 16:19 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class SqlQueryService {

  public SqlQueryResult query(SqlQueryParam param, PageParam pageParam) {
    return null;
  }

  public byte[] exportQueryResult(SqlQueryParam param, PageParam pageParam) {
    return new byte[0];
  }
}
