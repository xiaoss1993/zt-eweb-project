package com.zt.eweb.rbac.domain.model.user;

import java.util.List;

/**
 * 用户-Repository接口
 *
 *
 * @date 2021-02-02
 **/
public interface UserRepository {

    /**
     * 通过用户ID获取用户
     *
     * @param userId
     * @return
     */
    User find(UserId userId);

    /**
     * 根据token获取用户
     *
     * @param token
     * @return
     */
    User find(Token token);

    /**
     * 根据手机号获取账号
     *
     * @param mobile
     * @return
     */
    List<User> find(Mobile mobile);

    /**
     * 保存
     *
     * @param user
     */
    UserId store(User user);

    /**
     * 删除
     *
     * @param userIds
     */
    void remove(List<UserId> userIds);
}
