package com.zt.eweb.rbac.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysAccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号Mapper
 *
 *
 * @date 2021-02-10
 **/
@Mapper
public interface SysAccountMapper extends BaseMapper<SysAccountDO> {
}
