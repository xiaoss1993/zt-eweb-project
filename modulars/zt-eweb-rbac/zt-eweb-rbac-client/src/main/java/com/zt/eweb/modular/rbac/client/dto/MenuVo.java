package com.zt.eweb.modular.rbac.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVo {
    private String id;

    private String pid;

    private String title;

    private String icon;

    private String href;

    private String openType;

    private String type;

    private String powerCode;

    private List<MenuVo> children;
}