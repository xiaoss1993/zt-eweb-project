package com.zt.eweb.rbac.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.eweb.rbac.infrastructure.persistence.entity.SysCaptchaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码Mapper
 *
 *
 * @date 2021-02-08
 **/
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptchaDO> {
}
