package com.zt.eweb.modular.rbac.domain.user.model;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.eweb.framework.mybatis.core.util.BeanUtils;
import com.zt.eweb.modular.rbac.domain.role.model.RoleId;
import com.zt.eweb.modular.rbac.infra.dal.dataobject.SysUserDO;
import com.zt.eweb.modular.rbac.infra.dal.mapper.SysUserMapper;

import java.util.List;

/**
 * 用户工厂
 *
 * @author haoxin
 * @date 2021-02-24
 **/
public class UserFactory {

    private SysUserMapper userRepository;

    public UserFactory(SysUserMapper userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(Mobile mobile, Email email, Password password, UserName userName, List<RoleId> roleIdList) {
        QueryWrapper<SysUserDO> queryWrapper = new QueryWrapper<>();

        List<User> users = BeanUtils.convertList(userRepository.selectList(queryWrapper), User.class);
        Account account;
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                if (ObjectUtil.isNotEmpty(user)) {
                    throw new RuntimeException("租户内账号已存在");
                }
            }
            account = users.get(0).getAccount();
        } else {
            account = new Account(mobile, email, password);
        }
        if (roleIdList == null || roleIdList.isEmpty()) {
            throw new RuntimeException("角色未分配");
        }
        return new User(userName, account, roleIdList);
    }

}
