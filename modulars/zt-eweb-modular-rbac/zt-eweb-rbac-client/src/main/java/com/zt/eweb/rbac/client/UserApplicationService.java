package com.zt.eweb.rbac.client;

import com.zt.eweb.rbac.client.command.PasswordCommand;
import com.zt.eweb.rbac.client.command.UserCommand;

import java.util.List;

/**
 * 用户应用服务接口
 *
 * 
 * @date 2021-02-09
 **/
public interface UserApplicationService {

    /**
     * 保存用户
     *
     * @param userCommand
     */
    void save(UserCommand userCommand);

    /**
     * 更新用户
     *
     * @param userCommand
     */
    void update(UserCommand userCommand);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);

    /**
     * 修改密码
     *
     * @param passwordCommand
     */
    void changePassword(PasswordCommand passwordCommand);
}
