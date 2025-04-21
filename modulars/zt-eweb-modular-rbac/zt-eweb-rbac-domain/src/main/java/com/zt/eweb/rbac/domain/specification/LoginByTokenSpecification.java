package com.zt.eweb.rbac.domain.specification;

import com.zt.eweb.framework.common.domain.AbstractSpecification;
import com.zt.eweb.rbac.domain.model.user.User;

/**
 * token登录Specification
 *
 * @author haoxin
 * @date 2021-02-20
 **/
public class LoginByTokenSpecification extends AbstractSpecification<User> {

    @Override
    public boolean isSatisfiedBy(User user) {
        if(user == null) {
            throw new IllegalArgumentException("用户未登录");
        }
        if(!user.getAccount().isTokenValid()) {
            throw new IllegalArgumentException("账号已过期，请重新登录");
        }
        if(!user.isEnable()) {
            throw new IllegalArgumentException("账号已被锁定,请联系管理员");
        }
        return true;
    }
}
