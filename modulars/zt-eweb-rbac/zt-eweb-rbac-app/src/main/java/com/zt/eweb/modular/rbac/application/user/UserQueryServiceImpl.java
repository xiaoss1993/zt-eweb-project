package com.zt.eweb.modular.rbac.application.user;

import com.zt.eweb.modular.rbac.client.user.UserQueryService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 18:01 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

  @Override
  public List<String> getPowerCodesByUserIdAndPowerType(Long aLong, int i) {
    return null;
  }

  @Override
  public List<String> getRoleCodesByUserIdAndRoleType(Long aLong, Object o) {
    return null;
  }
}
