package com.zt.eweb.rbac.adapter.common;

import com.zt.eweb.rbac.client.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用Controller
 *
 * @author haoxin
 * @date 2021-02-04
 **/
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取当前用户
     *
     * @return
     */
    protected UserDTO getUser() {
        return null;
    }

}
