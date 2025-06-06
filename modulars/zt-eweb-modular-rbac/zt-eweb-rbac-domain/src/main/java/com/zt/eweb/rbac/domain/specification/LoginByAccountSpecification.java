package com.zt.eweb.rbac.domain.specification;

import com.zt.eweb.framework.common.domain.AbstractSpecification;
import com.zt.eweb.rbac.domain.model.user.User;

/**
 * 账号登录Specification
 *
 * 
 * @date 2021-02-20
 **/
public class LoginByAccountSpecification extends AbstractSpecification<User> {

    private String password;

    public LoginByAccountSpecification(String password) {
        this.password = password;
    }


    @Override
    public boolean isSatisfiedBy(User user) {
        if(!user.getAccount().checkPassword(password)) {
            throw new IllegalArgumentException("用户或密码不正确");
        }
        return true;
    }
}
