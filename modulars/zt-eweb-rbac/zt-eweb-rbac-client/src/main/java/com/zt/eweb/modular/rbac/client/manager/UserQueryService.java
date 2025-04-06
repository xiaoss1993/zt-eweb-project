package com.zt.eweb.modular.rbac.client.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.eweb.modular.rbac.client.dto.RbacUserDto;
import com.zt.eweb.modular.rbac.client.dto.SysUserDTO;
import com.zt.eweb.modular.rbac.client.dto.UserDataPower;

import java.util.List;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/5 14:28 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/5      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public interface UserQueryService {

    List<UserDataPower> getUserDataPowers(Long userId);

    String getById(Long loginId);

    List<String> getPowerCodesByUserIdAndPowerType(Long userId, Integer roleType);

    List<String> getRoleCodesByUserIdAndRoleType(Long userId, Integer roleType);

    RbacUserDto getUserInfoById(long loginIdAsLong);

    Page<SysUserDTO> page(Page<SysUserDTO> roadPage, QueryWrapper<SysUserDTO> queryWrapper);
}
