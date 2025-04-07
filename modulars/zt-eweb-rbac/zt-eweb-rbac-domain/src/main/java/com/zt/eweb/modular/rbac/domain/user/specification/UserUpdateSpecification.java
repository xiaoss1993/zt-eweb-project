package com.zt.eweb.modular.rbac.domain.user.specification;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/4/7 20:20
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/4/7      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */

import com.zt.eweb.framework.common.base.domain.AbstractSpecification;
import com.zt.eweb.modular.rbac.domain.user.model.User;

/**
 * 用户修改Specification
 *
 * @author haoxin
 * @date 2021-02-27
 **/
public class UserUpdateSpecification extends AbstractSpecification<User> {

    @Override
    public boolean isSatisfiedBy(User user) {
        return false;
    }
}
