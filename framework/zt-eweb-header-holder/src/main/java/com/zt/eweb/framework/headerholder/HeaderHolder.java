package com.zt.eweb.framework.headerholder;

import com.zt.eweb.framework.common.code.WebHeaderCode;
import com.zt.eweb.framework.headerholder.local.HolderContextHolder;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求头获取工具类
 *
 */
@UtilityClass
public class HeaderHolder {

    /**
     * 获取幂等令牌
     */
    public String getIdempotentToken() {
        return getHeader(WebHeaderCode.IDEMPOTENT_TOKEN);
    }

    /**
     * 获取请求头参数
     */
    public String getHeader(String name) {

        // TTL
        String ttlHeader = HolderContextHolder.get(name);
        if (Objects.nonNull(ttlHeader)) {
            return ttlHeader;
        }

        // web
        String webHeader = getWebHeader(name);
        if (Objects.nonNull(webHeader)) {
            return webHeader;
        }
        return null;
    }

    private String getWebHeader(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getHeader(name);
    }

}
