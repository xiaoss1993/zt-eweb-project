package com.zt.eweb.modular.rbac.client.user;

import java.util.List;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 18:00 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public interface UserQueryService {

  List<String> getPowerCodesByUserIdAndPowerType(Long aLong, int i);

  List<String> getRoleCodesByUserIdAndRoleType(Long aLong, Object o);
}
