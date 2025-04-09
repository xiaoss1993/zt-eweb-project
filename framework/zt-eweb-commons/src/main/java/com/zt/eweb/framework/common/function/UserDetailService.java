package com.zt.eweb.framework.common.function;

import com.zt.eweb.framework.common.entity.UserDetail;
import java.util.Optional;

/**
 * 获取用户
 *
 * 
 * @since 2022/8/28
 */
public interface UserDetailService {

    /**
     * 获取用户信息
     */
    Optional<UserDetail> findByUserId(Long userId);

}
