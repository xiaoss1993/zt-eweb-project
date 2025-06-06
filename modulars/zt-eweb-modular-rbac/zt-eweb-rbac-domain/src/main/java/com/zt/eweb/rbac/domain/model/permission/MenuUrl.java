package com.zt.eweb.rbac.domain.model.permission;

import com.zt.eweb.framework.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 菜单地址
 *
 * 
 * @date 2021-02-08
 **/
public class MenuUrl implements ValueObject<MenuUrl> {

    private String url;

    public MenuUrl(final String url) {
        if(StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("菜单地址不能为空");
        }
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean sameValueAs(MenuUrl other) {
        return other != null && this.url.equals(other.url);
    }

    @Override
    public String toString() {
        return url;
    }
}
