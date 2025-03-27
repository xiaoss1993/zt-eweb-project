package com.zt.eweb.modular.rbac.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.modular.rbac.dal.entity.RbacRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 模块名 :
 * 文件名 :    RbacRoleMapper
 * 创建时间 : 2025/3/15 10:14
 * 实现功能 :
 * RbacRole ORM接口
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/15      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Mapper
public interface RbacRoleMapper extends BaseMapper<RbacRole> {

}
