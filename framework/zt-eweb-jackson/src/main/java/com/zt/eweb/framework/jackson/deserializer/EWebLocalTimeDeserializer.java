package com.zt.eweb.framework.jackson.deserializer;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * java时间反序列化, 支持反序列化秒和毫秒级别的时间
 * 
 * @since 2024/1/16
 */
public class EWebLocalTimeDeserializer extends LocalTimeDeserializer {

    /**
     * 反序列化
     */
    @Override
    protected LocalTime _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
        try {
            return LocalTime.parse(string0, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        } catch (DateTimeParseException e) {
            return LocalTime.parse(string0, DatePattern.NORM_TIME_FORMATTER);
        }
    }
}
