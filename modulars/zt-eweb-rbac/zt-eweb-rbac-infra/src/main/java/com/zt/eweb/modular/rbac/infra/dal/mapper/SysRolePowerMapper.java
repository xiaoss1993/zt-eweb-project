package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysRolePower;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author laker
 * @since 2021-08-11
 */
public interface SysRolePowerMapper extends BaseMapper<SysRolePower> {

    @Select("   select p.power_code " +
            "        from sys_role_power rp " +
            "                 inner join sys_power p on rp.power_id = p.menu_id " +
            "                 inner join sys_role r on rp.role_id = r.role_id " +
            "        where p.type = #{type} " +
            "          and rp.role_id in " +
            "              (select ur.role_id " +
            "               from sys_user_role ur " +
            "               where ur.user_id = #{userId})")
    List<String> getPowerCodesByUserIdAndPowerType(Long userId, Integer type);
}
