package com.zt.eweb.framework.mybatis.core.typehandler;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * Jackson 实现 JSON 字段类型处理器, 会记录对象属性类型, 通常用于储存不确定对象的属性上
 * 例如: object对象, 泛型对象、存储的数据是字段声明类型的子类等
 * 如果在使用知道明确类型的包装类是，如List<T>、Set<T>， 请使用 JacksonTypeReferenceHandler
 * @see
 */
@Slf4j
@MappedTypes({ Object.class })
@MappedJdbcTypes(value = { JdbcType.VARCHAR, JdbcType.LONGVARCHAR })
public class JacksonRawTypeHandler extends AbstractJsonTypeHandler<Object> {


    private final Class<?> type;
    public JacksonRawTypeHandler(Class<?> type) {
        super(type);
        if (log.isTraceEnabled()) {
            log.trace("JacksonRawTypeHandler(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null");
        this.type = type;
    }

    public JacksonRawTypeHandler(Class<?> type, Field field) {
        super(type, field);
        if (log.isTraceEnabled()) {
            log.trace("JacksonRawTypeHandler(" + type + ")");
        }
        Assert.notNull(type, "Type argument cannot be null");
        this.type = type;
    }

    @Override
    public Object parse(String json) {
        return null;
    }

    @Override
    public String toJson(Object obj) {
        return null;
    }
}
