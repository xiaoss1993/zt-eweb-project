package com.zt.eweb.framework.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Distance implements IEnum<String> {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1);
    private final String unit;
    private final double meters;

    Distance(String unit, double meters) {
        this.unit = unit;
        this.meters = meters;
    }

    @Override
    @JsonValue
    public String getValue() {
        return unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}