package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zt.eweb.framework.mybatis.core.mapper.BaseCrudMapper;
import com.zt.eweb.framework.mybatis.core.mapper.DynamicQueryMapper;
import com.zt.eweb.modular.rbac.client.dto.UserDataPower;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户Mapper
 *
 * @author haoxin
 * @date 2021-01-23
 **/
@Mapper
public interface SysUserMapper extends BaseCrudMapper<SysUserDO> {

    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    IPage<SysUserDO> queryPage(IPage<SysUserDO> page, @Param("params") Map<String, Object> params);

    /**
     * 查询用户
     *
     * @param params
     * @return
     */
    SysUserDO queryUser(@Param("params") Map<String, Object> params);

    /**
     * 查询用户(不包含租户)
     *
     * @param params
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    List<SysUserDO> queryUserNoTenant(@Param("params") Map<String, Object> params);

    /**
     * 根据用户id获取其下的数据权限
     *
     * @param userId
     * @return
     */
    @Select("select p.* from sys_power p" +
            "                   inner join sys_role_power rw on rw.power_id = p.menu_id" +
            "                   inner join sys_user_role ur on rw.role_id = ur.role_id" +
            "                   inner join sys_role r on r.role_id = ur.role_id" +
            "            where ur.user_id = #{userId} and r.role_type = 2")
    List<UserDataPower> getUserDataPowers(Long userId);

    @Select("select p.power_code from sys_role_power rp " +
            "inner join sys_power p on rp.power_id = p.menu_id " +
            "inner join sys_role r on rp.role_id = r.role_id " +
            "where p.type = #{type} and rp.role_id in (select ur.role_id from sys_user_role ur where ur.user_id = #{userId})")
    List<String> getPowerCodesByUserIdAndPowerType(Long userId, int type);

    @Select("        select role_code " +
            "        from sys_role r " +
            "        inner join sys_user_role ur on ur.role_id = r.role_id " +
            "        where ur.user_id = #{userId} " +
            "        <if test=\"roleType != null\"> " +
            "            and r.role_type = #{roleType} " +
            "        </if>")
    List<String> getRoleCodesByUserIdAndRoleType(Long userId, Integer roleType);
}
