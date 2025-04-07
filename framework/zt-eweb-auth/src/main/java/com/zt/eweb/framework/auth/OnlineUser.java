package com.zt.eweb.framework.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OnlineUser {
    private String loginId;
    private String userId;
    private String nickName;
    private String ip;
    private String os;
    private String city;
    private String browser;
    private String tokenValue;
    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;
    /**
     * 最近一次操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastActivityTime;
}
