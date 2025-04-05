package com.zt.eweb.modular.rbac.infra.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysCaptchaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码Mapper
 *
 * @author haoxin
 * @date 2021-02-08
 **/
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptchaDO> {
}
