
package com.zt.eweb.modular.binder.service.impl;

import com.zt.eweb.framework.mybatis.core.service.impl.BaseServiceImpl;
import com.zt.eweb.modular.binder.entity.User;
import com.zt.eweb.modular.binder.mapper.UserMapper;
import com.zt.eweb.modular.binder.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 员工相关Service
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
