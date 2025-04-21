package com.zt.eweb.rbac.application.assembler;

import com.zt.eweb.rbac.domain.model.user.User;
import com.zt.eweb.rbac.client.dto.LoginSuccessDTO;

/**
 * Assembler class for the LoginSuccessDTOAssembler.
 *
 * @author haoxin
 * @date 2021-02-09
 **/
public class LoginSuccessDTOAssembler {

    public static LoginSuccessDTO toDTO(final User user) {
        final LoginSuccessDTO dto = new LoginSuccessDTO(
                user.getAccount().getToken().getToken(),
                String.valueOf(user.getAccount().getToken().getExpirePeriod()),
                user.getTenantId().getId());
        return dto;
    }
}
