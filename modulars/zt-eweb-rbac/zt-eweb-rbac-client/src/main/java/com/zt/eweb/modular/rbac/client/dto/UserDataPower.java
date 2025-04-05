package com.zt.eweb.modular.rbac.client.dto;

import com.zt.eweb.framework.common.constant.enums.DataFilterTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataPower {
    private String powerCode;
    private DataFilterTypeEnum dataFilterType;
}
